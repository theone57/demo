package com.all.lin.sign.stu._15command.lin.command;

import com.all.lin.sign.stu._15command.lin.CommandContext;

/**
 * @author linpu
 * @dateTime 2021-10-19 10:18
 * @email im.linpu@qq.com
 * @description 命令接口
 */
public interface Command {

    //执行动作(操作)
    void execute(CommandContext context);

}

