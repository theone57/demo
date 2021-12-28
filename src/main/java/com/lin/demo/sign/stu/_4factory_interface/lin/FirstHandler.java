package com.lin.demo.sign.stu._4factory_interface.lin;

import org.springframework.stereotype.Component;

/**
 * @author linpu
 * @dateTime 2021/12/28 16:13
 * @email im.linpu@qq.com
 * @description
 */
@Component(value = "factory-FirstHandler")
public class FirstHandler implements Handler {
    @Override
    public PlatformEnum setPlatformType() {
        return PlatformEnum.FIRST_PLATFORM;
    }

    @Override
    public String out() {
        return "FirstHandler out ...";
    }
}
