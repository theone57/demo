package com.lin.demo.sign.lp.pipeline._15command;

import com.lin.demo.DemoApplication;
import com.lin.demo.sign.stu._15command.lin.Client;
import com.lin.demo.sign.stu._15command.lin.CommandContext;
import com.lin.demo.sign.stu._15command.lin.CommandTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author linpu
 * @dateTime 2021-10-19 11:26
 * @email im.linpu@qq.com
 * @description
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class MyCommandTest {
    @Test
    public void test() {

        Client.build(CommandTypeEnum.LIGHT).method(new CommandContext("lin","18","m"));
    }
}

