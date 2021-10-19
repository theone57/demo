package com.lin.demo.sign.stu._15command.lin.command;

import com.lin.demo.sign.stu._15command.lin.CommandContext;
import com.lin.demo.sign.stu._15command.lin.solider.TvSolider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author linpu
 * @dateTime 2021-10-19 14:07
 * @email im.linpu@qq.com
 * @description
 */
@Component
public class HomeCommand implements Command {
    @Autowired
    private LightCommand lightCommand;
    @Autowired
    private TvSolider tvSolider;
    @Override
    public void execute(CommandContext context) {
        System.out.println("HomeCommand 执行....");
        lightCommand.execute(context);
        tvSolider.method2(context);
    }
}

