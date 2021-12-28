package com.all.lin.sign.stu._15command._03command;

import java.io.Serializable;

/**
 * 抽象命令类，由于需要将命令对象写入文件，因此它实现了Serializable接口，保证其序列化
 * 
 * @author w1992wishes
 * @created @2017年11月3日-下午5:24:04
 *
 */
public abstract class Command implements Serializable {
    private static final long serialVersionUID = -4023087706968880848L;
    protected String name; // 命令名称
    protected String args; // 命令参数
    protected Operator operator; // 维持对接收者对象的引用

    public Command(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    /**
     * 抽象的执行方法execute()，带参数
     * 
     * @param args
     */
    public abstract void execute(String args);

    /**
     * 抽象的执行方法execute()，不带参数
     * 
     * @param args
     */
    public void execute() {
        execute(this.args);
    }
}