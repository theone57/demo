package com.all.lin.statrter.config;

import com.alibaba.ttl.TtlRunnable;
import com.alibaba.ttl.threadpool.TtlExecutors;
import com.all.lin.statrter.async.AsyncExceptionHandler;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;

/**
 * @author linpu
 * @program
 * @description 异步配置类
 * @date 2021-04-29 00:34
 */
@Configuration
@EnableAsync
public class MyAsyncConfig implements AsyncConfigurer {
    public static void main(String[] args) throws InterruptedException {
        // 创建一个 StopWatch 实例并开始计时
        StopWatch sw = StopWatch.createStarted();

        // 休眠1秒
        Thread.sleep(1000);

        // 1002ms
        System.out.printf("耗时: %02d ms.\n", 1L);
    }

    @Autowired
    private AsyncExceptionHandler asyncExceptionHandler;

    /**
     * 配置线程池，可以创建ThreadPoolExecutor
     * 默认ThreadPoolTaskExecutor，通过TaskExecutionAutoConfiguration自动化配置类创建出来的
     * The {@link Executor} instance to be used when processing async
     * method invocations.
     */
//    @Override
//    @Bean("lazyTraceExecutor")
//    public Executor getAsyncExecutor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        //此方法返回可用处理器的虚拟机的最大数量; 不小于1
//        int core = Runtime.getRuntime().availableProcessors();
//        executor.setCorePoolSize(core);//设置核心线程数
//        executor.setMaxPoolSize(core * 2 + 1);//设置最大线程数
//        executor.setKeepAliveSeconds(3);//除核心线程外的线程存活时间
//        executor.setQueueCapacity(40);//如果传入值大于0，底层队列使用的是LinkedBlockingQueue,否则默认使用SynchronousQueue
//        executor.setThreadNamePrefix("my-executor-");//线程名称前缀
//        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());//设置拒绝策略
////        return new LazyTraceThreadPoolTaskExecutor(beanFactory, executor);
//        executor.initialize();
//
//        return executor;
//    }

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

    /**
     * {@code
     *
     * @Resource(name = "myTaskExecutor")
     * private ThreadPoolTaskExecutor myTaskExecutor;
     * private static Executor wrappedCasTaskExecutor;
     * // ---
     * @PostConstruct private void initExecutor() {
     * wrappedCasTaskExecutor = TtlExecutors.getTtlExecutor(myTaskExecutor);
     * }
     * }
     */
    @Bean
    public BeanPostProcessor threadPoolTaskExecutorBeanPostProcessor() {
        return new BeanPostProcessor() {

            @Override
            public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
                if (!(bean instanceof ThreadPoolTaskExecutor)) {
                    return bean;
                }
                // TransmittableThreadLocal
                ThreadPoolTaskExecutor executor = (ThreadPoolTaskExecutor) bean;
                executor.setTaskDecorator(TtlRunnable::get);
                return executor;
            }

        };
    }

    @Bean()
    @Qualifier("myForkJoinPool")
    public ExecutorService myForkJoinPool() {
        return TtlExecutors.getTtlExecutorService(new ForkJoinPool(10));
    }
}

