package com.all.lin.sign.lp.spring.retry;

import com.all.DemoApplication;
import com.all.lin.spring.retry.TestRetryServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author linpu
 * @dateTime 2021/12/28 16:10
 * @email im.linpu@qq.com
 * @description
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class RetryTest {


    @Resource
    private TestRetryServiceImpl retryService;

    @Test
    public void retryTest() throws Exception {
        retryService.test(0);
    }
}
