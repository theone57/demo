package com.lin.demo.juc.multithreading.demo01caixk;

import java.util.concurrent.CountDownLatch;

/**
 * 这也是juc包下的并发工具，主要有两个常用方法，countDown和await
 * 简单说下原理：CountDownLatch底层维护了一个计数器count，在实例化的时候设置，当调用countDown方法时，count减一，如果count在减一前已经为0，那么什么都不会发生，如果减一后变成0，则唤醒所有等待的线程；await方法会使当前线程等待，直到count为0
 * ————————————————
 * 版权声明：本文为CSDN博主「负债程序猿」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
 * 原文链接：https://blog.csdn.net/qq_33709582/article/details/121900989
 */
@SuppressWarnings("all")
public class CountDownLatchTest {
    static double year;

    public static void main(String[] args) {
    	//实例化一个CountDownLatch，count设置为1，也就是说，只要调用一次countDown方法就会唤醒线程
        CountDownLatch latch = new CountDownLatch(1);
        //线程A，练习唱跳rap
        Thread threadA = new Thread(() -> {
            for (year = 0.5; year <= 5; year += 0.5) {
                System.out.println("阿鸡开始练习唱跳rap：已练习" + year + "年");
                try {
                    Thread.sleep(288);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //众所周知，练习两年半即可出道
                if (year == 2.5) {
                    System.out.println("===========================>练习时长两年半，阿鸡出道！！！");
                    //计数器减一
                    latch.countDown();
                    //老规矩，去掉break触发隐藏关卡
                    break;
                }
            }
        });
        //线程B，练习打篮球
        Thread threadB = new Thread(() -> {
            try {
                //阻塞当前线程，计数器为0时被唤醒
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("阿鸡开始练习打篮球");
        });
        threadA.start();
        threadB.start();
    }
}
