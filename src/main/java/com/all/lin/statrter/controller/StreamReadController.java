package com.all.lin.statrter.controller;

import cn.hutool.core.io.IoUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author linpu
 * @dateTime 2022-11-27 20:53
 * @email im.linpu@qq.com
 * @description
 */
@RestController
@RequestMapping("stream")
public class StreamReadController {
    @PostMapping("/xml")
    public String checkInsuranceCallBack(HttpServletRequest request) {
        try {
            String xml = IoUtil.readUtf8(request.getInputStream());
            System.out.println("xml = " + xml);
            System.out.println("第二次读取\n"+IoUtil.readUtf8(request.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "ok";
    }
}
