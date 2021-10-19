package com.lin.demo.sign.stu._15command.lin.solider;

import com.lin.demo.sign.stu._15command.lin.CommandContext;

/**
 * @author linpu
 * @dateTime 2021-10-19 10:21
 * @email im.linpu@qq.com
 * @description 士兵接口
 */
public interface Solider {

    default void on(CommandContext commandContext) {
        throw new UnsupportedOperationException("新兵不支持此操作, 请覆写");
    }

    default void msg(CommandContext commandContext) {
        throw new UnsupportedOperationException("新兵不支持此操作, 请覆写");
    }
}

