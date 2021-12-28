package com.all.lin.juc.sut01.vol;

/**
 * 死循环: 每次循环开始初始化上面的四个变量的值为0
 * 初始化变量下,启用两个线程:设置a=1;x=b 线程2设置b=1,y=a
 * 线程1设置先执行,线程2设置后执行
 * 如果是正常的执行 x =0 ; y =1
 *
 * @author lin
 * @version v1.0
 * @program juc
 * @description jvm有序性
 * @date 2021-03-31 23:40
 */
public class VolatileOrderDemo {


    static volatile int a, b;//保证加了volatile的变量指令,重排时他们出现的顺序
    static int x, y;

    public static void main(String[] args) throws InterruptedException {

        int i = 0;
        while (true) {
            i++;
            a = b = x = y = 0;
            Thread thread1 = new Thread(() -> {
                a = 1;
                x = b;
            }, "a");

            Thread thread2 = new Thread(() -> {
                b = 1;
                y = a;
            }, "b");

            thread1.start();
            thread2.start();

            thread1.join();
            thread2.join();

            System.out.println("第" + i + "次打印：x=" + x + ", y=" + y);

            if (x == 0 && y == 0) {//如果出现x和y都=0,表示指令重排了
                break;
            }
        }
    }
}

