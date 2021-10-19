package com.lin.demo.sign.stu._15command.lin;

import com.lin.demo.sign.stu._15command.lin.command.HomeCommand;
import com.lin.demo.sign.stu._15command.lin.command.LightCommand;
import com.lin.demo.sign.stu._15command.lin.command.TvCommand;

/**
 * @author linpu
 * @dateTime 2021-10-19 10:50
 * @email im.linpu@qq.com
 * @description 命令枚举
 */

public enum CommandTypeEnum {
    LIGHT("light", LightCommand.class),
    TV("tv", TvCommand.class),
    Home("home", HomeCommand.class),

    ;
    private String description;
    private Class<?> clazz;

    CommandTypeEnum(String description, Class<?> clazz) {
        this.description = description;
        this.clazz = clazz;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }
}

