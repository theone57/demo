package com.all.lin.statrter.async.completable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * @author linpu
 * @dateTime 2022-06-30 10:40
 * @email im.linpu@qq.com
 * @description
 */
@Service
//@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CompletableFutureImpl {

    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    @Qualifier("myTaskExecutor")
//    @Lazy
    public void setThreadPoolTaskExecutor(ThreadPoolTaskExecutor threadPoolTaskExecutor) {
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
    }

    public void completableFuture() {

        System.out.println("f. begin ...");

        // 创建异步执行任务，有返回值
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("f1. come in ...");

            System.out.println("supplyAsync" + Thread.currentThread().getName());
            return "yes";
        }, threadPoolTaskExecutor);

        CompletableFuture<Void> future2 = future1.thenRunAsync(() -> {
            System.out.println("f2. come in ...");

        }, threadPoolTaskExecutor);

        CompletableFuture<Void> future3 = CompletableFuture.runAsync(() -> {
            System.out.println("f3. come in ...");
        }, threadPoolTaskExecutor);

        CompletableFuture.allOf(future1, future2, future3);

        System.out.println("f. end ...");

    }


}

