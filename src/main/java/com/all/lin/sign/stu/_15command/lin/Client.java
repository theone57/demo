package com.all.lin.sign.stu._15command.lin;

import cn.hutool.extra.spring.SpringUtil;
import com.all.lin.sign.stu._15command.lin.command.Command;

/**
 * @author linpu
 * @dateTime 2021-10-19 10:48
 * @email im.linpu@qq.com
 * @description 装配者
 * 命令模式属于对象的行为模式。命令模式又称为行动(Action)模式或者交易(Transaction)模式。
 * <p>
 * 命令模式把一个请求或者操作封装到一个对象中。命令模式允许系统使用不同的请求把客户端参数化，对请求排队或者记录请求日志，可以提供命令的撤销和恢复功能。
 * <p>
 * 链接：https://www.jianshu.com/p/5901e76a6348
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

