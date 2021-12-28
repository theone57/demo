package com.all.lin.sign.stu._15command.lin.solider;

import com.all.lin.sign.stu._15command.lin.CommandContext;
import org.springframework.stereotype.Component;

/**
 * @author linpu
 * @dateTime 2021-10-19 10:52
 * @email im.linpu@qq.com
 * @description
 */
@Component
public class FirstLightSolider implements Solider {
    @Override
    public void on(CommandContext commandContext) {
        System.out.println("-> FirstLightSolider 执行...");
    }
}


