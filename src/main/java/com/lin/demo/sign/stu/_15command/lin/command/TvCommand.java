package com.lin.demo.sign.stu._15command.lin.command;

import com.lin.demo.sign.stu._15command.lin.CommandContext;
import com.lin.demo.sign.stu._15command.lin.solider.TvSolider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author linpu
 * @dateTime 2021-10-19 14:03
 * @email im.linpu@qq.com
 * @description
 */
@Component
public class TvCommand implements Command {
    @Autowired
    private TvSolider tvSolider;
    @Override
    public void execute(CommandContext context) {
        System.out.println("执行 TvCommand。。。");
        tvSolider.method2(context);
    }
}

