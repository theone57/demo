package com.all.lin.juc.sut01.thread;

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
class Ticket {
    //票数
    private int num = 20;


    //卖票方法
    public synchronized void sale() {


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


            //4. 卖票成功 -1
            System.out.println(Thread.currentThread().getName() + "卖票成功...剩余:" + --num);

    }

}

/**
 * 1. 判断票数是否足够
 * 2. 如果票数足够,执行卖票业务
 * 3. 卖票成功,票数-1
 */
public class SynchronizedSDemo {
    // 在main方法中创建多线程方法，测试卖票业务
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
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

