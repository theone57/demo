package com.lin.demo.juc.sut01.thread;

import java.util.concurrent.TimeUnit;

class Phone {

    public static synchronized void sendSMS() throws Exception {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("------sendSMS");
    }

    public synchronized void sendEmail() throws Exception {
        System.out.println("------sendEmail");
    }

    public void getHello() {
        System.out.println("------getHello");
    }

}

/**
 * 多线程的8个问题：
 * <p>
 * 1. 标准访问，先打印短信还是邮件
 * 2. 停4秒在短信方法内，先打印短信还是邮件
 * sleep是握着锁睡,senSms先执行获取到了锁不会释放
 * 3. 普通的hello方法，是先打短信还是hello
 * hello()方法不需要锁
 * 4. 现在有两部手机，先打印短信还是邮件
 * synchronized 默认锁的是调用方法的对象,两个线程使用的资源类对象不一样,不能互相锁
 * 5. 两个静态同步方法，1部手机，先打印短信还是邮件
 * static 方法锁的是类模板对象
 * 6. 两个静态同步方法，2部手机，先打印短信还是邮件
 * 7. 1个静态同步方法，1个普通同步方法，1部手机，先打印短信还是邮件
 * 两个方法锁的对象不一样
 * 8. 1个静态同步方法，1个普通同步方法，2部手机，先打印短信还是邮件
 * -----------
 * synchronized 8锁问题
 * 对于成员方法:使用synchronized,锁得是当前调用方法的对象
 * 对于静态方法,使用synchronized,锁的是类模板对象
 * 对于同步代码块,使用synchronized,指定那个对象锁那个对象
 *
 * 不同对象的成员方法没有锁的竞争关系
 * 静态方法无论是类调用还是不同对象调用都必须竞争锁
 */
public class Lock_8 {

    public static void main(String[] args) throws Exception {

        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "AA").start();

        //主线程休眠,AA线程一定比BB线程先启动
        Thread.sleep(100);

        new Thread(() -> {
            try {
//                phone.sendEmail();
//                phone.getHello();
                phone2.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "BB").start();
    }
    /**
     * 总结：
     *
     * synchronized实现同步的基础：Java中的每一个对象都可以作为锁。具体表现为以下3种形式：
     *
     * 1. 对于普通同步方法，锁是当前实例对象。
     * 2. 对于静态同步方法，锁是当前类的Class对象。
     * 3. 对于同步方法块，锁是Synchonized括号里配置的对象
     *
     * 当一个线程试图访问同步代码块时，它首先必须得到锁，退出或抛出异常时必须释放锁。
     *
     * 也就是说：
     *
     * ​		如果**一个实例对象**的**非静态同步方法**获取锁后，该实例对象的其他非静态同步方法必须等待获取锁的方法释放锁后才能获取锁；
     * 可是**不同实例对象**的非静态同步方法因为用的**是不同对象的锁**，所以毋须等待其他实例对象的非静态同步方法释放锁，就可以获取自己的锁。
     *
     * ​		**所有的静态同步方法用的是同一把锁——类对象本身**。不管是不是同一个实例对象，只要是一个类的对象，一旦一个静态同步方法获取锁之后，其他对象的静态同步方法，都必须等待该方法释放锁之后，才能获取锁。
     *
     * ​		而静态同步方法（Class对象锁）与非静态同步方法（实例对象锁）之间是不会有竞态条件的。
     */
}