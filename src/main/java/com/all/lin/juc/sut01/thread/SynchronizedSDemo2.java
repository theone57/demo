package com.all.lin.juc.sut01.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * <font color="red">多线程编程模板上</font>：
 * <p>
 * 1. 线程 操作 资源类
 * 2. 高内聚低耦合
 * <p>
 * 实现步骤：
 * <p>
 * 1. 创建资源类
 * 2. 资源类里创建同步方法、同步代码块
 * 3. 多线程调用
 *
 * @author lin
 * @version v1.0
 * @program juc
 * @description
 * @date 2021-03-30 19:20
 */
//资源类: 买票程序
class Ticket2 {
    //票数
    private int num = 20;
    ReentrantLock lock = new ReentrantLock();

    //卖票方法
    public void sale() {

        lock.lock();
        try {
            //1. 检查 票数是否足够
            if (num <= 0) {
                System.out.println("票已售罄");
                return;
            }
            //2. 开始卖票
            System.out.println(Thread.currentThread().getName() + "开始卖票");
            //3. 卖票业务操作
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //检查账户安全
            check();

            //4. 卖票成功 -1
            System.out.println(Thread.currentThread().getName() + "卖票成功...剩余:" + --num);
        } finally {
            lock.unlock();
        }
    }

    /**
     * ### 测试可重入性
     * <p>
     * 可重入锁又名递归锁，是指在同一个线程在外层方法获取锁的时候，再进入该线程的内层方法会自动获取锁。
     * Java中ReentrantLock和synchronized都是可重入锁，可重入锁的一个优点是**可一定程度避免死锁**。
     * ---------------------------------
     * A类中有两个普通同步方法，都需要对象a的锁。如果是不可重入锁的话，
     * aa方法首先获取到锁，aa方法在执行的过程中需要调用bb方法，此时锁被aa方法占有，
     * bb方法无法获取到锁，这样就会导致bb方法无法执行，aa方法也无法执行，出现了死锁情况。可重入锁可避免这种死锁的发生。
     */
    public void check() {
        lock.lock();
        try {
            System.out.println("正在检查账户安全....");
        } finally {
            lock.unlock();
        }
    }
}

/**
 * 1. 判断票数是否足够
 * 2. 如果票数足够,执行卖票业务
 * 3. 卖票成功,票数-1
 */
public class SynchronizedSDemo2 {
    // 在main方法中创建多线程方法，测试卖票业务
    public static void main(String[] args) {
        Ticket2 ticket = new Ticket2();
        //启动三个线程买票
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                ticket.sale();
            }

        }, "WIN1").start();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                ticket.sale();
            }

        }, "WIN2").start();

        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                ticket.sale();
            }

        }, "WIN3").start();
    }
}

