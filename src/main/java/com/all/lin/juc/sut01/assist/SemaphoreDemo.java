package com.all.lin.juc.sut01.assist;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * public Semaphore(int permits) // 构造方法，permits指资源数目（信号量）
 * public void acquire() throws InterruptedException // 占用资源，当一个线程调用acquire操作时，它要么通过成功获取信号量（信号量减1），要么一直等下去，直到有线程释放信号量，或超时。
 * public void release() // （释放）实际上会将信号量的值加1，然后唤醒等待的线程。
 *
 * ---------------------
 * 信号量主要用于两个目的：
 *
 * 1. 多个共享资源的互斥使用。
 * 2. 用于并发线程数的控制。保护一个关键部分不要一次输入超过N个线程
 * @author lin
 * @version v1.0
 * @program juc
 * @description
 * @date 2021-03-31 16:31
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
//        Semaphore semaphore = new Semaphore(3);//模拟资源有三个空车位
        Semaphore semaphore = new Semaphore(3);//模拟资源有三个空车位

        // 6个线程，模拟6辆车
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                try {
                    //抢占一个车位
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " 抢到了一个停车位！！");
                    // 停一会儿车
                    TimeUnit.SECONDS.sleep(new Random().nextInt(10));

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 开走，释放一个停车位
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName() + " 离开停车位！！");

                }
            },String.valueOf(i)).start();
        }
    }
}

