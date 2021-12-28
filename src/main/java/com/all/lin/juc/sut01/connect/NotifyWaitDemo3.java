package com.all.lin.juc.sut01.connect;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ## 3.4. 定制化调用通信
 * <p>
 * 案例：
 * <p>
 * ​		多线程之间按顺序调用，实现A->B->C。三个线程启动，要求如下：
 * <p>
 * ​		AA打印5次，BB打印10次，CC打印15次
 * <p>
 * ​		接着
 * <p>
 * ​		AA打印5次，BB打印10次，CC打印15次
 * <p>
 * ​		。。。打印10轮
 * <p>
 * <p>
 * 分析实现方式：
 * <p>
 * 1. 有一个锁Lock，3把钥匙Condition
 * 2. 有顺序通知（切换线程），需要有标识位
 * 3. 判断标志位
 * 4. 输出线程名 + 内容
 * 5. 修改标识符，通知下一个
 *
 * @author lin
 * @version v1.0
 * @program juc
 * @description
 * @date 2021-03-30 21:28
 */
class ShareDataOne3 {
    private Integer number = 0;

    //一个锁Lock，3把钥匙Condition
    private Integer flag = 1; // 线程标识位，通过它区分线程切换
    private final Lock lock = new ReentrantLock();
    private final Condition condition1 = lock.newCondition();
    private final Condition condition2 = lock.newCondition();
    private final Condition condition3 = lock.newCondition();

    public void printA5() {
        lock.lock();
        try {
            while (flag != 1) {

                condition1.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(i + ": AA");
            }
            //唤醒2
            flag = 2;
            condition2.signal();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            lock.unlock();
        }
    }

    public void printB10() {

        lock.lock();

        try {
            while (flag != 2) {
                condition2.await();
            }

            for (int i = 0; i < 10; i++) {
                System.out.println(i + ": BB");
            }


            flag = 3;
            //唤醒3
            condition3.signal();

        } catch (Exception e) {

            e.printStackTrace();

        } finally {
            lock.unlock();
        }
    }

    public void printC15() {

        lock.lock();

        try {
            while (flag != 3) {
                condition3.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(i + ": CC");
            }

            flag = 1;
            //唤醒3
            condition1.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

/**
 * 多线程之间按顺序调用，实现A->B->C
 * 三个线程启动，要求如下：
 * AA打印5次，BB打印10次，CC打印15次
 * 接着
 * AA打印5次，BB打印10次，CC打印15次
 * ......来10轮
 */
public class NotifyWaitDemo3 {

    public static void main(String[] args) {
        ShareDataOne3 shareDataOne3 = new ShareDataOne3();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareDataOne3.printA5();
            }

        },"AAA").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareDataOne3.printB10();
            }

        },"BBB").start();


        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareDataOne3.printC15();
            }

        },"CCC").start();
    }


}

