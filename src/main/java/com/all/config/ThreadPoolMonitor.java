package com.all.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author linpu
 * @dateTime 2021-06-03 17:28
 * @email im.linpu@qq.com
 * @description
 */
@Slf4j
@Component
public class ThreadPoolMonitor implements CommandLineRunner {
    @Resource(name = "myTaskExecutor")
    private ThreadPoolTaskExecutor taskExecutor;

    @Override
    public void run(String... args) {
        poolMonitor();
    }

    private void poolMonitor() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("thread-pool-status-print" + "-%d")
                .setDaemon(true).build();
        ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1, threadFactory);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            log.info("=========================");
            log.info("myTaskExecutor Pool 总线程数: [{}]", taskExecutor.getPoolSize());
            log.info("myTaskExecutor Pool 活跃线程数: {}", taskExecutor.getActiveCount());
            log.info("myTaskExecutor Pool 已执行任务总数 : {}", taskExecutor.getThreadPoolExecutor().getCompletedTaskCount());
            log.info("myTaskExecutor Pool 队列任务数量: {}", taskExecutor.getThreadPoolExecutor().getQueue().size());
            log.info("=========================");
        }, 5, 1, TimeUnit.MINUTES);
    }
}
