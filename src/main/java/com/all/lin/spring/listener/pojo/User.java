package com.all.lin.spring.listener.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author linpu
 * @dateTime 2021-06-03 16:55
 * @email im.linpu@qq.com
 * @phone 17602155862
 * @description 用户类
 */
@Data
@Accessors(chain = true)
public class User {
    @NotBlank(message="名称不能为空")
    private String name;

    @NotNull(message="age参数名不能为空")
    private Integer age;

    private Integer sex;

    private String email;

    private String phone;

    private String address;

}

