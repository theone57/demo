package com.all.lin.spring.async;

import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.TimeUnit;

/**
 * @author linpu
 * @dateTime 2022-05-11 15:46
 * @email im.linpu@qq.com
 * @description
 */
public class AsyncTestServiceImpl {

    @Async
    public String executor1() {
        try {
            System.out.println("executor1方法开始执行。。。。");
            TimeUnit.SECONDS.sleep(4);
            System.out.println("executor1方法结束执行===========================");
        } catch (InterruptedException e) {
            //return AsyncResult.forExecutionException(e);
        }
        return "hello executor1";
    }

    @Async
    public String exeuctor2() {
        try {
            System.out.println("executor2方法开始执行。。。。");
            TimeUnit.SECONDS.sleep(5);
            int i = 1 / 0;
            System.out.println("executor2方法结束执行===========================");
        } catch (InterruptedException e) {
            //return AsyncResult.forExecutionException(e);
            e.printStackTrace();
        }
        return "hello executor2";
    }
}
