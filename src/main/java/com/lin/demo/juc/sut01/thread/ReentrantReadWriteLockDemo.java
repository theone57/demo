package com.lin.demo.juc.sut01.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 测试读写锁
 * <p>
 * 需求:
 * 缓存类,有两个方法
 * 一个方法,读缓存
 * 一个方法写缓存
 * <p>
 * 并发读无需枷锁
 * 并发写需要将并发读和写锁起来
 */
class MyCache {

    private volatile Map<String, String> cache = new HashMap<>();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(String key, String value) {
        //添加写锁: 一旦有写操作,会锁所有的读和写
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 开始写入！");

//            Thread.sleep(300);
            TimeUnit.MILLISECONDS.sleep(300);

            cache.put(key, value);
            System.out.println(Thread.currentThread().getName() + " 写入成功！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void get(String key) {
        //读锁
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 开始读出！");
            Thread.sleep(300);
            String value = cache.get(key);
            System.out.println(Thread.currentThread().getName() + " 读出成功！" + value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }
}

public class ReentrantReadWriteLockDemo {

    public static void main(String[] args) {

        MyCache cache = new MyCache();

        for (int i = 1; i <= 10; i++) {

            String num = String.valueOf(i);

            // 开启10个写线程
            new Thread(() -> {
                cache.put(num, num);
            }, "A").start();
        }

        //error:::: B 读出成功！null
        for (int i = 1; i <= 10; i++) {
            String num = String.valueOf(i);
            // 开启10个读线程
            new Thread(() -> {
                cache.get(num);
            }, "B").start();
        }
    }
}