package com.all.lin.sign.stu._15command._03command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 请求发送者
 * 
 * @author w1992wishes
 * @created @2017年11月3日-下午5:31:20
 *
 */
public class OperatorWindow {
    // 定义一个集合来存储每一次操作时的命令对象
    private List<Command> commands = new ArrayList<Command>();
    private Command command;

    // 设置具体命令对象
    public void setCommand(Command command) {
        this.command = command;
    }

    // 执行命令，同时将命令对象添加到命令集合中
    public void call(String args) {
        command.execute(args);
        commands.add(command);
    }

    // 记录请求日志，将命令集合写入日志文件
    public void save() {
        FileUtil.writeCommands(commands);
    }

    // 从日志文件中提取命令集合，并调用所有命令的execute()方法来实现命令的重新执行
    public void recover() {
        List<Command> commands = FileUtil.readCommands();

        for (Command command : commands) {
            command.execute();
        }
    }
}

/**
 * 文件操作类
 * 
 * @author w1992wishes
 * @created @2017年11月3日-下午5:32:16
 *
 */
class FileUtil {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);
    
    public static void writeCommands(List<Command> commands) {
        try {
            FileOutputStream fos = new FileOutputStream("operator.log");
            ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(fos));
            oos.writeObject(commands);
            oos.close();
        } catch (Exception e) {
            LOGGER.error("writeCommands error!", e);
        }
    }

    public static List<Command> readCommands() {
        try {
            FileInputStream fis = new FileInputStream("operator.log");
            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(fis));
            @SuppressWarnings("unchecked")
            List<Command> commands = (List<Command>) ois.readObject();
            ois.close();
            return commands;
        } catch (Exception e) {
            LOGGER.error("readCommands error!", e);
            return null;
        }
    }
}