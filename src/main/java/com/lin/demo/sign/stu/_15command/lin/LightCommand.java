package com.lin.demo.sign.stu._15command.lin;

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
    private LightSolider lightSolider;

    @Override
    public void execute(CommandContext context) {
        lightSolider.method1(context);
    }
}

