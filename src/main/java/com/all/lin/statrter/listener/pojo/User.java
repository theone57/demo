package com.all.lin.statrter.listener.pojo;

import cn.hutool.extra.validation.ValidationUtil;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.ConstraintViolation;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Set;

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
    @NotBlank(message = "{user.name.notBlank}")
    private String name;

//    @NotNull(message = "age参数名不能为空")
    private Integer age;

    private Integer sex;

    private String email;

    private String phone;

    private String address;

    @DecimalMax("10")
    @DecimalMin("0.5")
    private BigDecimal money;

    public static void main(String[] args) {
        User user = new User();
        user.setAddress(null);
        user.setAge(19);
//        String s = JSONObject.toJSONString(user);
//        System.out.println("s = " + s);
//        String str = "{\n" +
//                "    \"age\":19,\n" +
//                "    \"email\":\"xxxxx\",\n" +
//                "    \"address\":null\n" +
//                "}";
//        User user1 = JSONObject.parseObject(str, User.class, Feature.DisableASM);
//        User user2 = JSONUtil.toBean(str, User.class);
//        System.out.println("user1 = " + user1);
//        System.out.println("user2 = " + user2);

        Set<ConstraintViolation<User>> validate = ValidationUtil.validate(user);
        System.out.println("validate = " + validate);
    }

}

