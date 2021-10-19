package com.lin.demo.sign.stu._15command.lin;

/**
 * @author linpu
 * @dateTime 2021-10-19 10:19
 * @email im.linpu@qq.com
 * @description 调用者
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
