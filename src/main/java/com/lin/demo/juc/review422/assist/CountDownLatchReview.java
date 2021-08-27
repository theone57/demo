package com.lin.demo.juc.review422.assist;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author lin
 * @version v1.0
 * @program juc
 * @description
 * @date 2021-04-22 12:04
 */
public class CountDownLatchReview {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(3);

        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                // 每个同学墨迹几秒钟
                try {
                    TimeUnit.SECONDS.sleep(new Random().nextInt(5));
                    System.out.println("出来了一位同学");
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            },String.valueOf(i)).start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("锁门");
    }
}

