package com.all.lin.juc.review422.assist;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author lin
 * @version v1.0
 * @program juc
 * @description
 * @date 2021-04-22 12:11
 */
public class SemaphoreReview {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        ExecutorService threadPool = Executors.newFixedThreadPool(6);
        for (int i = 0; i < 10; i++) {
            threadPool.execute(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " 抢到了一个停车位！！");
                    //停车时间
                    TimeUnit.SECONDS.sleep(new Random().nextInt(10));

                    System.out.println(Thread.currentThread().getName()+"离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //离开车位
                    semaphore.release();
                }
            });

        }
        threadPool.shutdown();
    }
}

