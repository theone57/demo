package com.all.lin.juc.sut01.threadpool;



import java.util.concurrent.*;

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
 * @description 自定义线程池
 */
public class ThreadPoolDemo2 {

    public static void main(String[] args) {
        //手动初始化线程池
        ExecutorService threadPool= new ThreadPoolExecutor(3, 5,
                2,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(2),
                Executors.defaultThreadFactory(), //线程工厂:一般使用默认的
//               new ThreadPoolExecutor.AbortPolicy() //默认的拒绝策略
//               new ThreadPoolExecutor.CallerRunsPolicy() //将某些任务回退到调用者
//                new ThreadPoolExecutor.DiscardOldestPolicy()//抛弃队列中等待最久的任务
                new ThreadPoolExecutor.DiscardPolicy()
               );

        //8个线程执行,线程池会初始化几个线程?
        for (int i = 0; i < 6; i++) {
            int finalI = i;
            threadPool.execute(()->{
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "working..." + finalI);
            });
        }
    }
}

