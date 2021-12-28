package com.all.lin.juc.multithreading.demo01caixk;
@SuppressWarnings("all")
public class JoinTest {
    // 用来记录啊鸡学习时间
    static double year;

    public static void main(String[] args) {
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
                    //留意下这个break，想想如果不break会怎样
                    break;
                }
            }
        });
        //线程B，练习打篮球
        Thread threadB = new Thread(() -> {
            try {
            	// 让threadA线程插队，threadB执行到这儿时会被阻塞，直到threadA执行完
                threadA.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("阿鸡开始练习打篮球");
        });
        //　启动线程
        threadA.start();
        threadB.start();
    }
}
