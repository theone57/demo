package com.lin.demo.sign.stu._15command.lin.solider;

import com.lin.demo.sign.stu._15command.lin.CommandContext;
import org.springframework.stereotype.Component;

/**
 * @author linpu
 * @dateTime 2021-10-19 10:52
 * @email im.linpu@qq.com
 * @description
 */
@Component
public class SecondLightSolider implements Solider {
    @Override
    public void on(CommandContext commandContext) {
        System.out.println("-> SecondLightSolider 执行...");
    }
}


