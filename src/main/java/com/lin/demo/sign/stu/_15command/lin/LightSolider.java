package com.lin.demo.sign.stu._15command.lin;

import org.springframework.stereotype.Component;

/**
 * @author linpu
 * @dateTime 2021-10-19 10:52
 * @email im.linpu@qq.com
 * @description
 */
@Component
public class LightSolider implements Solider{
    @Override
    public void method1(CommandContext commandContext) {
        System.out.println("light execute ...");
        System.out.println("commandContext = " + commandContext);
    }
}


