package com.all.lin.spring.listener.controller;

import com.all.lin.spring.listener.pojo.User;
import com.all.lin.spring.listener.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linpu
 * @dateTime 2021-06-03 17:34
 * @email im.linpu@qq.com
 * @phone 17602155862
 * @description
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 新增用户操作
     * @param user
     */
    @PostMapping("add")
    public void add(User user) {
        userService.addUser(user);
    }
}

