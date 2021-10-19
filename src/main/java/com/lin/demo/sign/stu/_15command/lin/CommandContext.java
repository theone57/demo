package com.lin.demo.sign.stu._15command.lin;

import com.lin.demo.sign.stu._15command.lin.thrid.MsgComponent;
import com.lin.demo.sign.stu._15command.lin.thrid.MsgTypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author linpu
 * @dateTime 2021-10-19 10:19
 * @email im.linpu@qq.com
 * @description 命令上下文
 */
@Data
@NoArgsConstructor
@ToString
public class CommandContext {
    private String name;
    private String age;
    private String gender;
    private MsgTypeEnum msgTypeEnum;
    private MsgComponent.MsgParam msgParam;

    public CommandContext(String name, String age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
}

