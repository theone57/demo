package com.lin.demo.sign.stu._15command.lin.command;

import com.lin.demo.sign.stu._15command.lin.CommandContext;
import com.lin.demo.sign.stu._15command.lin.solider.FirstLightSolider;
import com.lin.demo.sign.stu._15command.lin.solider.SecondLightSolider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author linpu
 * @dateTime 2021-10-19 10:52
 * @email im.linpu@qq.com
 * @description
 */
@Component
public class LightCommand implements Command {
    @Autowired
    private FirstLightSolider firstLightSolider;
    @Autowired
    private SecondLightSolider secondLightSolider;

    @Override
    public void execute(CommandContext context) {
        System.out.println("LightCommand 执行....");
        firstLightSolider.method1(context);
        secondLightSolider.method1(context);
    }
}

