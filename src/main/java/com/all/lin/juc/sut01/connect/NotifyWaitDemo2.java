package com.all.lin.juc.sut01.connect;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lin
 * @version v1.0
 * @program juc
 * @description
 * @date 2021-03-30 21:28
 */
class ShareDataOne2 {
    private Integer number = 0;
    final Lock lock = new ReentrantLock();//初始化lock锁
    final Condition c1 = lock.newCondition();//初始化condition对象

    //+1
    public void increment() throws InterruptedException {
        //判断
        lock.lock();
        try {
            while (number != 0) {
                c1.await();
            }
            // 2. 干活
            number++;
            System.out.println(Thread.currentThread().getName() + ": " + number);

            // 3. 通知
            c1.signalAll();
        } finally {
            lock.unlock();
        }

    }
    //+1

    public synchronized void decrement() throws InterruptedException {
        lock.lock();
        //判断
        try {
            while (number != 1) {
                c1.await();
            }
            // 2. 干活
            number--;
            System.out.println(Thread.currentThread().getName() + ": " + number);

            // 3. 通知
            c1.signalAll();
        } finally {
            lock.unlock();
        }

    }
}

/**
 * 现在两个线程，
 * 可以操作初始值为零的一个变量，
 * 实现一个线程对该变量加1，一个线程对该变量减1，
 * 交替，来10轮。
 * <p>
 * 笔记：Java里面如何进行工程级别的多线程编写
 * 1 多线编程模板（套路）-----上
 * 1.1  线程    操作    资源类
 * 1.2  高内聚  低耦合
 * 2 多线程编程模板（套路）-----中
 * 2.1  判断
 * 2.2  干活
 * 2.3  通知
 * 3 多线程编程模板（套路）-----下
 * 防止虚假唤醒（while）
 */
public class NotifyWaitDemo2 {

    public static void main(String[] args) {
        ShareDataOne2 shareDataOne = new ShareDataOne2();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    shareDataOne.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "AAA").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    shareDataOne.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "BBB").start();

        /**
         * ##  虚假唤醒
         *
         * 换成4个线程会导致错误，**虚假唤醒**
         *
         * 原因：wait()会释放锁, 在java多线程判断时，不能用if，程序出事出在了判断上面。
         *
         * **注意，消费者被唤醒后是从wait()方法（被阻塞的地方）后面执行，而不是重新从同步块开头
         */
        //增加到4哥线程 ==>>error
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    shareDataOne.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "CCC").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    shareDataOne.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "DDD").start();
    }
}

