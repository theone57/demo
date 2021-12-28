package com.all.lin.sign.stu._4factory_interface.lin;

/**
 * @author linpu
 * @dateTime 2021-09-10 13:54
 * @email im.linpu@qq.com
 * @description 保险转换接口
 */
public interface Handler {

    PlatformEnum setPlatformType();

    // 其他执行

    default void print() {
        System.out.println("hello world from Handler");
    }

    String out();
}

