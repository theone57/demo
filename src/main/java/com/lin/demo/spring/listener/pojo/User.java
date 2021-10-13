package com.lin.demo.spring.listener.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

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
    private String name;

    private Integer age;

    private Integer sex;

    private String email;

    private String phone;

    private String address;

}

