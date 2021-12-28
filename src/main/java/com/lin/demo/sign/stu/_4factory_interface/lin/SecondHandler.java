package com.lin.demo.sign.stu._4factory_interface.lin;

import org.springframework.stereotype.Component;

/**
 * @author linpu
 * @dateTime 2021/12/28 16:14
 * @email im.linpu@qq.com
 * @description
 */
@Component(value = "factory-SecondHandler")
public class SecondHandler implements Handler {
    @Override
    public PlatformEnum setPlatformType() {
        return PlatformEnum.SECOND_PLATFORM;
    }

    @Override
    public String out() {
        return "SecondHandler out ...";
    }
}