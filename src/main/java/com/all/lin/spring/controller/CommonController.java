package com.all.lin.spring.controller;

import com.all.lin.exception.BusinessException;
import com.all.lin.spring.listener.pojo.User;
import com.all.lin.spring.validator.ValidatorUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linpu
 * @dateTime 2022-05-06 16:02
 * @email im.linpu@qq.com
 * @description
 */
@RestController
@RequestMapping("common")
public class CommonController {
    /**
     * 新增用户操作
     *
     * @param user
     */
    @PostMapping("/validate")
    public void test(User user) {
        try {
            ValidatorUtils.validateEntity(user);
        } catch (BusinessException e) {
            e.printStackTrace();
        }

//        userService.addUser(user);
        System.out.println(" come in");
    }
}
