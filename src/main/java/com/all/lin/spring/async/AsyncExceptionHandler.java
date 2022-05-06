package com.all.lin.spring.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 返回值为ListenableFuture的异步方法可以使用异步回调处理异常结果，那么返回值为普通类型的异步方法出现异常该如何处理呢？
 * <p>
 * springTask提供了AsyncUncaughtExceptionHandler 接口，达到对异步调用异常的统一处理。
 * <p>
 * 注意：AsyncUncaughtExceptionHandler 只能拦截**返回类型非 Future** 的异步调用方法。
 * <p>
 * 返回类型为 Future 的异步调用方法，请使用异步回调来处理。
 * <p>
 * 实现步骤：
 * <p>
 * 1. 自定义异常处理实现类实现AsyncUncaughtExceptionHandler 接口
 * 2. 添加配置类（@Configuration）实现AsyncConfigurer异步配置接口
 *
 * @author lin
 * @version v1.0
 * @program
 * @description
 * @date 2021-04-29 00:35
 */
@Slf4j
@Component(value = "AsyncExceptionHandlerByLin")
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        //记录 日志 或 记录到 数据库
        log.error("[demo-service] ==>> AsyncExceptionHandler]===>>异步任务出现异常 ==>> 方法：{}，参数：{},异常信息：{}", method, params, ex.getMessage());

    }
}

