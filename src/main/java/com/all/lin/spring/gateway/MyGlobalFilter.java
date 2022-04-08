package com.all.lin.spring.gateway;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.all.lin.utils.IpUtils;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * @ClassName: MyGlobalFilter
 * @Description: 自定义gateway全局过滤器, 全局过滤器无需配置, 对所有的路由都生效
 * @Author: TKQ
 * @Create_time: 14:30 2021-03-02
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MyGlobalFilter implements GlobalFilter, Ordered {

    private final StringRedisTemplate stringRedisTemplate;

    /**
     * 请求频率限制时间
     */
    private static final Integer OFFSET = 3;

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取当前请求的url
        String requestUrl = exchange.getRequest().getURI().toString();
        if (requestUrl.contains("chat-record/timCallBack")) {
            log.debug("{} 请求进入 {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")),requestUrl);
        }
        //TODO 校验token合法性等等,自定义逻辑
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        String methodName = serverHttpRequest.getMethodValue();
        String contentType = serverHttpRequest.getHeaders().getFirst("Content-Type");
        URI uri = serverHttpRequest.getURI();
        //post请求拦截判断重复调用
        if(HttpMethod.POST.name().equals(methodName) && !Objects.requireNonNull(contentType).startsWith("multipart/form-data")){
            AtomicReference<String> bodyRef = new AtomicReference<>();
            return DataBufferUtils.join(exchange.getRequest().getBody())
                    .flatMap(dataBuffer -> {
                        CharBuffer charBuffer = StandardCharsets.UTF_8.decode(dataBuffer.asByteBuffer());
                        DataBufferUtils.retain(dataBuffer);
                        bodyRef.set(charBuffer.toString());
                        String bodyStr = formatStr(bodyRef.get());
                        Flux<DataBuffer> cachedFlux = Flux
                                .defer(() -> Flux.just(dataBuffer.slice(0, dataBuffer.readableByteCount())));
                        // 缓存请求到redis
                        String clientIp = IpUtils.getIpAddr((HttpServletRequest) serverHttpRequest);
                        log.info("重复请求过滤器;url={},ip ={}", uri.toString(), clientIp);
                        String key = clientIp + "-" + uri.toString();
                        if (stringRedisTemplate.hasKey(key)) {
                            //判断缓存参数是否相同
                            if (StringUtils.isEmpty(bodyStr)) {
                                //参数为空存入key
                                bodyStr = key;
                            }
                            String param = stringRedisTemplate.opsForValue().get(key);
                            if (bodyStr.equals(param)) {
                                //参数相同表示重复请求，再次刷新缓存时间
                                stringRedisTemplate.opsForValue().set(key, bodyStr);
                                stringRedisTemplate.expire(key, OFFSET, TimeUnit.SECONDS);
                                //自定义响应结果
                                ServerHttpResponse response = exchange.getResponse();
                                JSONObject message = new JSONObject();
                                message.set("status", -1);
                                message.set("data", "重复请求");
                                byte[] bits = JSONUtil.toJsonStr(message).getBytes(StandardCharsets.UTF_8);
                                DataBuffer buffer = response.bufferFactory().wrap(bits);
                                response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
                                //指定编码，否则在浏览器中会中文乱码
                                response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
                                return response.writeWith(Mono.just(buffer));
                            }
                        }
                        //第一次请求放入缓存
                        stringRedisTemplate.opsForValue().set(key, bodyStr);
                        stringRedisTemplate.expire(key, OFFSET, TimeUnit.SECONDS);
                        //封装request，传给下一级
                        ServerHttpRequest mutatedRequest = new ServerHttpRequestDecorator(
                                exchange.getRequest()) {
                            @Override
                            public Flux<DataBuffer> getBody() {
                                return cachedFlux;
                            }
                        };
                        return chain.filter(exchange.mutate().request(mutatedRequest).build());
                    });
        }

        //放行请求
        return chain.filter(exchange);
    }

    /**
     * 去掉空格,换行和制表符
     * @param str
     * @return
     */
    private static String formatStr(String str){
        if (str != null && str.length() > 0) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            return m.replaceAll("");
        }
        return str;
    }


    @Override
    public int getOrder() {
        //设置最高级别,优先执行该过滤器,防止gateway读取body后后续的过滤器不能正常获取body
        return Ordered.HIGHEST_PRECEDENCE;
    }

    /**
     * 方法一：
     * 网上有网友说这种方式最多能获取1024字节的数据，数据过长会被截断，
     * 导致数据丢失。这里笔者没有亲自验证过，只是把这种方式提供在这里供大家参考。
     */
    private String getBodyContent(ServerWebExchange exchange){
        Flux<DataBuffer> body = exchange.getRequest().getBody();
        AtomicReference<String> bodyRef = new AtomicReference<>();
        // 缓存读取的request body信息
        body.subscribe(dataBuffer -> {
            CharBuffer charBuffer = StandardCharsets.UTF_8.decode(dataBuffer.asByteBuffer());
            DataBufferUtils.release(dataBuffer);
            bodyRef.set(charBuffer.toString());
        });
        //获取request body
        return bodyRef.get();
    }

    /**
     * 方法二：
     * 读取字节方式拼接字符串
     */
    private String resolveBodyFromRequest(ServerHttpRequest serverHttpRequest){
        //获取请求体
        Flux<DataBuffer> body = serverHttpRequest.getBody();
        StringBuilder sb = new StringBuilder();

        body.subscribe(buffer -> {
            byte[] bytes = new byte[buffer.readableByteCount()];
            buffer.read(bytes);
            String bodyString = new String(bytes, StandardCharsets.UTF_8);
            sb.append(bodyString);
        });
        return formatStr(sb.toString());
    }


}
