package com.lin.demo.sign.stu._15command.lin.thrid;

import lombok.Getter;

/**
 * @author linpu
 * @dateTime 2021-10-19 14:30
 * @email im.linpu@qq.com
 * @description
 */
@Getter
public enum MsgTypeEnum {
    Home("打开房间所有设备"),
    LIGHT("打开房间所有灯"),
    TV("打开电视机"),
    ;
    private String description;

    MsgTypeEnum(String description) {
        this.description = description;
    }
}
