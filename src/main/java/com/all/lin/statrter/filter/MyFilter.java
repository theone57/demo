//package com.all.lin.spring.filter;
//
//import com.alibaba.fastjson.JSON;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//@Order(3)
//@WebFilter(urlPatterns = "/*", filterName = "filter")
//public class MyFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//
//        ServletRequest requestWrapper = null;
//        String param = "";
//        if (request instanceof HttpServletRequest) {
//            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//            String method = httpServletRequest.getMethod().toUpperCase();
//            String type = httpServletRequest.getContentType();
//            if ("POST".equals(method) && StringUtils.equalsAnyIgnoreCase(type, "application/json")) {
//                requestWrapper = new BodyReaderHttpServletRequestWrapper(
//                        (HttpServletRequest) request);
//            }
//        }
//
//        if (requestWrapper == null) {
//            Map<String, String[]> originRequestMap = request.getParameterMap();
//            Map<String, String> requestMap = new HashMap<String, String>();
//            for (String key : originRequestMap.keySet()) {
//                String[] values = originRequestMap.get(key);
//                requestMap.put(key, values[0]);
//            }
//            param = JSON.toJSONString(requestMap);
//        } else {
//            param = ((BodyReaderHttpServletRequestWrapper) requestWrapper).getBody();
//        }
//        System.out.println("过滤器:" + param);
//        //放行
//        if (requestWrapper == null) {
//            chain.doFilter(request, response);
//        } else {
//            chain.doFilter(requestWrapper, response);
//        }
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}