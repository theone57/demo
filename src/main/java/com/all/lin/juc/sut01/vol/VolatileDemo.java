package com.all.lin.juc.sut01.vol;

/**
 * 如果一个字段被声明为volatile，
 * Java线程内存模型确保所有线程看到这个变量的值是一致的。
 * -----------------------
 * 为volatile 可以保证可见性,可以通知气筒线程,主物理内存的值已经被修改
 *
 * @author lin
 * @version v1.0
 * @program juc
 * @description
 * @date 2021-03-31 21:20
 */
public class VolatileDemo {

    private static Integer flag = 1;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            System.out.println("我是子线程工作内存flag的值：" + flag);
            while (flag == 1) {
                //https://blog.csdn.net/qq_29951485/article/details/90236345
//                System.out.println("come in");
            }
            System.out.println("子线程操作结束..." + flag);

        }).start();
        Thread.sleep(500);
        flag = 2;
        System.out.println("我是主线程工作内存flag的值：" + flag);
    }
}

