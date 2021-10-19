package com.lin.demo.sign.stu._15command.lin;

import cn.hutool.extra.spring.SpringUtil;

/**
 * @author linpu
 * @dateTime 2021-10-19 10:48
 * @email im.linpu@qq.com
 * @description
 */
public class Client {
    Invoker invoker;

    public Client(Invoker invoker) {
        this.invoker = invoker;
    }
    public static Client build(CommandTypeEnum commandTypeEnum) {
        Invoker invoker = new Invoker((Command) SpringUtil.getBean(commandTypeEnum.getClazz()));
        return new Client(invoker);
    }

    public void method(CommandContext commandContext) {
        invoker.perform(commandContext);
    }
}

