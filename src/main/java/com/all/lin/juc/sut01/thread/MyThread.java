package com.all.lin.juc.sut01.thread;

/**
 * **创建线程常用两种方式**：
 * 1. 继承Thread：java是单继承，资源宝贵，要用接口方式
 * 2. 实现Runable接口
 * @author lin
 * @version v1.0
 * @program juc
 * @description
 * @date 2021-03-30 18:59
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " working...");
    }
}
class ThreadDemo1{
    public static void main(String[] args) {
        //启动线程
        //run()自动帮助自动调用
        new MyThread().start();
    }
}
