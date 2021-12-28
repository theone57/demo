package com.all.lin.juc.sut01.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * ThreadPoolExecutor自带的拒绝策略如下：
 *
 * 1. AbortPolicy(默认)：直接**抛出RejectedExecutionException异常**阻止系统正常运行
 * 2. CallerRunsPolicy：“调用者运行”一种调节机制，该策略既不会抛弃任务，也不会抛出异常，而是**将某些任务回退到调用者**，从而降低新任务的流量。
 * 3. DiscardOldestPolicy：**抛弃队列中等待最久的任务**，然后把当前任务加人队列中 尝试再次提交当前任务。
 * 4. DiscardPolicy：**该策略默默地丢弃无法处理的任务**，不予任何处理也不抛出异常。 如果允许任务丢失，这是最好的一种策略。
 *
 * **以上内置的策略均实现了RejectedExecutionHandler接口，也可以自己扩展RejectedExecutionHandler接口，定义自己的拒绝策略**
 * @author lin
 * @version v1.0
 * @program juc
 * @description
 * @date 2021-03-31 19:42
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
//        //Executor : ExecutorService
//        //newCachedThreadPool : 线程数量为Integer 最大值 线程池 ; 会自动扩容,但是扩容数量最大是Integer.MAX_VALUE
//        ExecutorService threadPool = Executors.newCachedThreadPool();

//        //newFixedThreadPool: 参数: 创建指定线程数的  线程池 ; 不会自动扩容
////        ExecutorService threadPool = Executors.newFixedThreadPool(1);

        //只有一个线程,只能特定场景下使用
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();
//
//        //创建100哥线程执行请求
//        for (int i = 0; i < 100; i++) {
//            threadPool.execute(()->{
//                //获取线程池中的一个线程任务执行任务
//                System.out.println(Thread.currentThread().getName()+ "working...");
//            });
//        }
//        //线程池如果失败关闭,程序不会停止
//        threadPool.shutdown();
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(3);
        threadPool.scheduleAtFixedRate(() -> {

            System.out.println(Thread.currentThread().getName() + "working," + System.currentTimeMillis());
        }, 3, 2, TimeUnit.SECONDS);

    }
}

