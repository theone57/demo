package com.lin.demo.juc.sut01.assist;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch是一个非常实用的多线程控制工具类，应用非常广泛。
 * <p>
 * 例如：在手机上安装一个应用程序，假如需要5个子进程检查服务授权，那么主进程会维护一个计数器，初始计数就是5。用户每同意一个授权该计数器减1，当计数减为0时，主进程才启动，否则就只有阻塞等待了。
 * <p>
 * CountDownLatch中count down是倒数的意思，latch则是门闩的含义。整体含义可以理解为倒数的门栓，似乎有一点“三二一，芝麻开门”的感觉。CountDownLatch的作用也是如此。
 * <p>
 * 常用的就下面几个方法：
 * new CountDownLatch(int count) //实例化一个倒计数器，count指定初始计数
 * countDown() // 每调用一次，计数减一
 * await() //等待，当计数减到0时，阻塞线程（可以是一个，也可以是多个）并行执行
 *
 *
 * -------------------------------
 * 面试：CountDownLatch 与 join 方法的区别
 *
 * ​		调用一个子线程的 **join()方法**后，该线程会一直**被阻塞直到该线程运行完毕**。
 * 而 CountDownLatch 则使用计数器允许子线程**运行完毕或者运行中**时候递减计数，也就是 CountDownLatch 可以在子线程运行任何时候让 await 方法返回而不一定必须等到线程结束；
 * 另外使用线程池来管理线程时候一般都是直接添加 Runnable 到线程池这时候就没有办法在调用线程的 join 方法了，countDownLatch 相比 Join 方法让我们对线程同步有更灵活的控制。
 * @author lin
 * @version v1.0
 * @program juc
 * @description
 * @date 2021-03-31 11:44
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        // 初始化计数器，初始计数为6
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {

                try {
                    // 每个同学墨迹几秒钟
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                    System.out.println(Thread.currentThread().getName() + " 同学出门了");

                    countDownLatch.countDown();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }

        // 调用计算器的await方法，等待6位同学都出来
        countDownLatch.await();

        System.out.println("值班同学锁门了");
    }
}

