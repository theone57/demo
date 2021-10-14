package com.lin.demo.sign.stu._25pipeline.pipeline_04.pipeline;

import com.lin.demo.sign.stu._25pipeline.pipeline_04.value.Value;

/**
 * @author linpu
 * @dateTime 2021-10-14 10:29
 * @email im.linpu@qq.com
 * @description
 */
public interface Pipeline extends Invoker {

    void addFirst(Value... value);

    void addLast(Value... value);
}
