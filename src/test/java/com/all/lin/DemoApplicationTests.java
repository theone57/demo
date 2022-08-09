package com.all.lin;

import com.all.DemoApplication;
import com.all.lin.statrter.mybatis.sensitiveword.SensitiveWordDO;
import com.all.lin.statrter.mybatis.sensitiveword.SensitiveWordMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;



//这是JUnit的注解，通过这个注解让SpringJUnit4ClassRunner这个类提供Spring测试上下文。
@RunWith(SpringJUnit4ClassRunner.class)
//这是Spring Boot注解，为了进行集成测试，需要通过这个注解加载和配置Spring应用上下
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = DemoApplication.class)
class DemoApplicationTests {
    @Autowired
    private SensitiveWordMapper sensitiveWordMapper;

    @Test
    public void contextLoads() {
        List<SensitiveWordDO> sensitiveWordDOS = sensitiveWordMapper.selectList();
        sensitiveWordDOS.forEach(System.out::println);
    }

}