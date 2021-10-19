package com.lin.demo.sign.stu._15command.lin;

import com.lin.demo.sign.stu._15command.lin.command.Command;

/**
 * @author linpu
 * @dateTime 2021-10-19 10:19
 * @email im.linpu@qq.com
 * @description 司令
 */
public class Invoker {
    Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    public void perform(CommandContext commandContext) {
        command.execute(commandContext);
    }
}
