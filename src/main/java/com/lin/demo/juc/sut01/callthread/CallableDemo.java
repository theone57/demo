package com.lin.demo.juc.sut01.callthread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * FutureTask：未来的任务，
 * 用它就干一件事，**异步调用。通常用它解决耗时任务，挂起堵塞问题。**
 * @author lin
 * @version v1.0
 * @program juc
 * @description
 * @date 2021-03-31 16:50
 */
public class CallableDemo {
    public static void main(String[] args) {
        // 创建多线程
//        new Thread(new MyRunnableThread(),"threadName").start();
        /*
        * Thread的构造器只能接收Runnable的实例参数
        * FutureTask实现了RunnableFuture接口,RunnableFuture又实现了Runnable接口
        * -- 由于futureTask间接实现了Runnable接口
        * --线程启动时,会自动调用传入线程中的Runnable实例的run()方法
        * --futureTask的run方法被调用时,它内部调用了传给FutureTask的Callable的call方法,
        * 并且将callable的call()方法执行的返回值或异常记录到futureTask对象中
        * --如果我们希望使用线程执行的结果,可以访问futureTak.get()获取
        *
        * */
        // 2. 创建Callable的实例，并用FutureTask类来包装Callable对象
        // 3. 创建FutureTask对象，需要一个Callable类型的参数
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new MyCallableThread());
        // 4. 创建多线程，由于FutureTask的本质是Runnable的实现类，所以第一个参数可以直接使用task
        new Thread(futureTask,"AA").start();
        //
        // 如果想打印threadName2的结果，即**不想复用之前的计算结果。怎么办？再创建一个FutureTask对象即可。**
        new Thread(futureTask,"BB").start();//**只计算一次**，FutureTask会复用之前计算过得结果
        new Thread(new FutureTask<Integer>(new  MyCallableThread()),"CC").start();
        try {
            //不会阻塞,判断子线程是否执行完毕
            while (!futureTask.isDone()) {
                //子线程还未执行完毕
                System.out.println("主线程等待...");
            }
            /*
             * get()阻塞方法
             *
             * 如果必须阻塞获取,推荐:一般将写在主线程的最后
             */
            Integer res = futureTask.get();
            System.out.println(res);


        } catch ( Exception e) {
            e.printStackTrace();
        }
    }
}
class MyRunnableThread implements Runnable{
    @Override
    public void run() {

    }
}
class MyCallableThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        TimeUnit.SECONDS.sleep(3);
        System.out.println(Thread.currentThread().getName() + "执行了！");
        return 200;
    }
}

