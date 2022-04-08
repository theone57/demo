package com.all.lin.spring.advice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linpu
 * @dateTime 2022-03-10 13:51
 * @email im.linpu@qq.com
 * @description
 */
@RestController
@RequestMapping("hello1")
public class HelloController1 {

    @GetMapping("/hello")
    public String getStr(){
        return "hello,javadaily";
    }


}
