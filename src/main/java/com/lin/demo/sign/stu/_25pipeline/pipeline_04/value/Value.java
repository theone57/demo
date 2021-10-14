package com.lin.demo.sign.stu._25pipeline.pipeline_04.value;

import com.lin.demo.sign.stu._25pipeline.pipeline_04.model.Context;

/**
 * @author linpu
 * @dateTime 2021-10-14 10:30
 * @email im.linpu@qq.com
 * @description 阀门
 */
public interface Value<T extends Context> {

    /**
     * 是否异步执行
     *
     * @return
     */
    default boolean isAsync() {
        return false;
    }

    /**
     * 是否可用
     *
     * @param context
     * @return
     */
    boolean isEnable(T context);

    /**
     * 处理
     *
     * @param context
     * @return
     */
    default boolean handle(T context) {
        return true;
    }

    /**
     * 处理器名
     *
     * @return
     */
    String getName();

    /**
     * 校验器名
     *
     * @param context
     */
    default void validate(T context) {
    }

    /**
     * 是否为批量投保
     *
     * @return
     */
    default boolean isBatchCast() {
        return false;
    }
}

