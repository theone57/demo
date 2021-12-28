package com.all.bean;

import com.all.lin.spring.async.AsyncExceptionHandler;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;

import java.util.concurrent.Executor;

/**
 * @author lin
 * @version v1.0
 * @program gmall-1026
 * @description 异步配置类
 * @date 2021-04-29 00:34
 */
@Configuration
public class AsyncConfig implements AsyncConfigurer {

    @Autowired
    private AsyncExceptionHandler asyncExceptionHandler;
//    @Bean
//    public Executor taskExecutor() {
//        ThreadPoolTaskExecutor threadPool = new ThreadPoolTaskExecutor();
//        //设置核心线程数
//        threadPool.setCorePoolSize(10);
//        //设置最大线程数
//        threadPool.setMaxPoolSize(100);
//        //线程池所使用的缓冲队列
//        threadPool.setQueueCapacity(10);
//        // 拒绝策略， 由当前执行线程执行
//        threadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
//        //等待任务在关机时完成--表明等待所有线程执行完
//        threadPool.setWaitForTasksToCompleteOnShutdown(true);
//        // 等待时间 （默认为0，此时立即停止），并没等待xx秒后强制停止
//        threadPool.setAwaitTerminationSeconds(60);
//        //  线程名称前缀
//        threadPool.setThreadNamePrefix("gbw");
//        // 初始化线程
//        threadPool.initialize();
//        return threadPool;
//    }
    /**
     * 配置线程池，可以创建ThreadPoolExecutor
     * 默认ThreadPoolTaskExecutor，通过TaskExecutionAutoConfiguration自动化配置类创建出来的
     * The {@link Executor} instance to be used when processing async
     * method invocations.
     */
    @Override
    public Executor getAsyncExecutor() {
        //todo 可以配置线程池
        return null;
    }

    /**
     * 配置异步未捕获异常处理器
     * The {@link AsyncUncaughtExceptionHandler} instance to be used
     * when an exception is thrown during an asynchronous method execution
     * with {@code void} return type.
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return asyncExceptionHandler;
    }
}

