package com.lin.demo.juc.sut01.thread;

/**
 * 对只有一个抽象方法的接口可以实现Lambada表达式简化实现类对象的创建
 * 语法:拷贝实现方法的小括号(形参列表,类型可以省略),右边写死右箭头,最后{方法的实现}
 * 接口如果以后要使用Lambada表达式创建对象,只能有一个抽象方法,为了避免创建多个抽象方法,
 * 可以在接口上添加@FunctionalInterface注解标注
 * 接口还可以又多个default和静态方法,必须实现
 * @author lin
 * @version v1.0
 * @program juc
 * @description
 * @date 2021-03-30 09:26
 */
public class MyRunnable implements Runnable {
    /**
     *
     */
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "正在执行......");
    }

}

class ThreadDemo {
    public static void main(String[] args) {
        //Runnable 1.创建实现类飙血功能
        MyRunnable myRunnable = new MyRunnable();
        new Thread(myRunnable, "testR").start();

        //2.
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "正在执行......");
            }
        }).start();

        //3.
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName() + "正在执行......");
        };
        new Thread(runnable).start();

        //4.
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "正在执行......");
        }).start();


    }
}

