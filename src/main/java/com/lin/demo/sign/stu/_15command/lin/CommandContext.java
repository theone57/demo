package com.lin.demo.sign.stu._15command.lin;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@ToString
public class CommandContext {
    private String name;
    private String age;
    private String gender;
}

