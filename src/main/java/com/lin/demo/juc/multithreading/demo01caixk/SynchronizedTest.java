package com.lin.demo.juc.multithreading.demo01caixk;

/**
 * wait() 和 notify()都是Object类的通讯方法，
 * 注意一点，wait和 notify需搭配synchronized使用，注意，notify不会释放锁，至于不会释放锁体现在哪儿，这个demo下面有说明
 * <p>
 * 这个threadA里面的break一定要多想想，跑一跑你就知道啥叫不会释放锁
 * 如果没有break，threadA在唤醒threadB后，会继续执行自己的逻辑，等自己执行完了才会释放锁，这时候threadB才开始执行
 */
@SuppressWarnings("all")
public class SynchronizedTest {
    static double year;

    public static void main(String[] args) {
        SynchronizedTest sync = new SynchronizedTest();
        sync.execute();
    }

    public void execute() {
        //线程A，练习唱跳rap
        Thread threadA = new Thread(() -> {
            synchronized (this) {
                for (year = 0.5; year <= 5; year += 0.5) {
                    try {
                        System.out.println("阿鸡开始练习唱跳rap：已练习" + year + "年");
                        Thread.sleep(288);
                        if (year == 2.5) {
                            System.out.println("===========================>练习时长两年半，阿鸡出道！！！");
                            //唤醒等待中的threadB，但threadB不会立马执行，而是等待threadA执行完，因为notify不会释放锁
                            notify();
                            break;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        //线程B，练习打篮球
        Thread threadB = new Thread(() -> {
            synchronized (this) {
                try {
                    wait();
                    System.out.println("阿鸡开始练习打篮球");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //注意，一定要先启动B，不然会导致B永远阻塞
        threadB.start();
        threadA.start();
    }
}
