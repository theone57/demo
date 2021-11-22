package com.lin.demo.threadlocal;

import cn.hutool.core.thread.ThreadUtil;
import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author linpu
 * @dateTime 2021-11-18 10:35
 * @email im.linpu@qq.com
 * @description ThreadLocal在开启子线程时，父线程向子线程值传递问题
 * <p>
 * https://blog.csdn.net/qq_26012495/article/details/104379137
 */
public class ThreadLocalProblem {

    public static void main(String[] args) throws Exception {

        notUseInheritableThreadLocal();

//        userInheritableDemo()
//        ;
        // 使用线程池
//        useThreadPool();

//        useTransmittable();
    }

    private static void notUseInheritableThreadLocal() {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("初始化的值能继承吗？");

        System.out.println("父线程的ThreadLocal值：" + threadLocal.get());

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程到了");
                System.out.println("子线程的ThreadLocal值：" + threadLocal.get());
            }
        }).start();

        ThreadUtil.sleep(1000);
    }


    private static void userInheritableDemo() {
        ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();
        threadLocal.set("初始化的值能继承吗？");

        System.out.println("父线程的ThreadLocal值：" + threadLocal.get());
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("子线程到了");
                System.out.println("子线程的ThreadLocal值：" + threadLocal.get());
            }
        }).start();
    }


    /**
     * 但是InheritableThreadLocal仍然存在一个问题，上述我们解决了在创建Thread时的传递，其传递时机是在创建线程时；但在线程池复用的情景中，没有创建Thread的触发条件。
     * <p>
     * 举一个失败案例：创建一个固定大小为2的线程池，进行五次子线程执行，每次给父线程的threadLocal重新赋值，由于线程池大小为2，因此只有前两次触发初始化线程池，后面三次均为线程复用，
     * 理论上来说，子线程可继承的父线程变量到i=1截止。
     */
    private static void useThreadPool() {
        ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

        ExecutorService threadPool = Executors.newFixedThreadPool(2);// 创建一个固定大小为2的线程池

        for (int i = 0; i < 5; i++) {
            threadLocal.set("初始化的值能继承吗？" + i);
            System.out.println("父线程的ThreadLocal值：" + threadLocal.get());


            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("子线程到了");
                    System.out.println("=========子线程的ThreadLocal值：" + threadLocal.get());
                }
            });

        }
    }


    private static void useTransmittable() {
        ThreadLocal<String> threadLocal = new TransmittableThreadLocal<>();

        ExecutorService threadPool = TtlExecutors.getTtlExecutorService(Executors.newFixedThreadPool(2));
        for (int i = 0; i < 5; i++) {
            threadLocal.set("初始化的值能继承吗？" + i);
            System.out.println("父线程的ThreadLocal值：" + threadLocal.get());
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("子线程到了");
                    System.out.println("=========子线程的ThreadLocal值：" + threadLocal.get());
                }
            });
        }
    }


}
