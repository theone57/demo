package com.all.lin.sign.lp._15command;

import com.all.DemoApplication;
import com.all.lin.sign.stu._15command.lin.Client;
import com.all.lin.sign.stu._15command.lin.CommandContext;
import com.all.lin.sign.stu._15command.lin.CommandTypeEnum;
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
        Client.build(CommandTypeEnum.Home).method(new CommandContext("lin","18","m"));
    }
}

