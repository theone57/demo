package com.lin.demo.sign.stu._15command.lin.solider;

import com.lin.demo.sign.stu._15command.lin.CommandContext;
import org.springframework.stereotype.Component;

/**
 * @author linpu
 * @dateTime 2021-10-19 14:04
 * @email im.linpu@qq.com
 * @description
 */
@Component
public class TvSolider implements Solider {
    @Override
    public void method2(CommandContext commandContext) {
        System.out.println("=========================");
        System.out.println("TvSolider 执行...");
        System.out.println("commandContext = " + commandContext);
        System.out.println("=========================");
    }
}

