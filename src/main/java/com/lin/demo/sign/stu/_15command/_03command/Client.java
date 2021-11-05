package com.lin.demo.sign.stu._15command._03command;

public class Client {
    public static void main(String args[]) {
        OperatorWindow window = new OperatorWindow(); // 请求发送者
        Command command; // 命令对象
        Operator operator = new Operator(); // 请求接收者

        // 具体命令
        command = new InsertCommand("insert");
        command.setOperator(operator);
        window.setCommand(command);
        window.call("节点1");

        command = new InsertCommand("insert");
        command.setOperator(operator);
        window.setCommand(command);
        window.call("节点2");

        command = new ModifyCommand("modify");
        command.setOperator(operator);
        window.setCommand(command);
        window.call("节点1");

        command = new DeleteCommand("delete");
        command.setOperator(operator);
        window.setCommand(command);
        window.call("节点2");

        System.out.println("---------------------保存操作记录---------------------");
        window.save();

        System.out.println("---------------------死机---------------------");

        System.out.println("---------------------恢复操作---------------------");
        window.recover();
    }
}