package com.all;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@SpringBootApplication
@EnableRetry
@EnableCaching
@RestController
@EnableDiscoveryClient
@EnableFeignClients
@EnableAsync
@EnableScheduling
@EnableTransactionManagement
//@MapperScan("com.lin.**.mapper")
public class DemoApplication {

    public static void main(String[] args) {
        // Spring boot通过application.yml设置的MyBatis日志的属性加载是晚于SqlSessionFactoryBean加载的，因此这个设置将会无效，因此在springboot启动之前加载这个日志九可以使用。
///        LogFactory.useCustomLogging(StdOutImpl.class);
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}
