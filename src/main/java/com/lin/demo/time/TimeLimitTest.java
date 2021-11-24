package com.lin.demo.time;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by mazhenhua on 2017/3/2.
 */
public class TimeLimitTest {

    private static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    public static void main(String[] args) {
        int timeLimit = 5; // 预计TaskA执行不得超过5秒。
        check(new TaskA("monitoThread"), timeLimit);
    }
    @SuppressWarnings("deprecation")
    public static void check(TaskA taskA, int timeLimit) {
        Thread subThread = new Thread(taskA);
        subThread.start(); // 启动子线程
        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();
        boolean timeout = endTime - startTime > timeLimit * 1000;
        try {
            // 如果子线程未运行完毕，且未超时，则继续等待子线程。
            while (subThread.isAlive() && !timeout) {
                subThread.join(1000);
                endTime = System.currentTimeMillis();
                timeout = endTime - startTime > timeLimit * 1000;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (timeout) {
            // 虽然该方法不推荐，但是对陷入死循环的线程也只能使用此方法来结束线程了。
            subThread.stop();
            String now = sdf.format(Calendar.getInstance().getTime());
            System.out.println(now + " - " + taskA.name
                    + " is timeout and is stopped.");
        }
    }
    public static class TaskA implements Runnable {
        private String name = "";
        public TaskA(String name) {
            this.name = name;
        }
        @Override
        public void run() {
            // 开始日志
            String now = sdf.format(Calendar.getInstance().getTime());
            System.out.println(now + " - " + name + " started.");
            // 模拟业务操作
            doSomthing();
            // 结束日志
            now = sdf.format(Calendar.getInstance().getTime());
            System.out.println(now + " - " + name + " ended.");
        }
    }

    public static void doSomthing(){
        long begin = System.currentTimeMillis();

        while (true) {
            /*System.out.println("死循环");*/
        }
    }
}

