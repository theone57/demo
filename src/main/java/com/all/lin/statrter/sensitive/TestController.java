package com.all.lin.statrter.sensitive;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sensitive")
public class TestController {
    @GetMapping("/test")
    public Person test() {
        Person user = new Person();
        user.setRealName("不才陈某");
        user.setPhoneNumber("19796328206");
        user.setAddress("浙江省杭州市....");
        user.setIdCard("4333333333334334333");
        return user;
    }
}