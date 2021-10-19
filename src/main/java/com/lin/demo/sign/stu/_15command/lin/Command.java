package com.lin.demo.sign.stu._15command.lin;

/**
 * @author linpu
 * @dateTime 2021-10-19 10:18
 * @email im.linpu@qq.com
 * @description
 */
public interface Command {

    //执行动作(操作)
    void execute(CommandContext context);

}

