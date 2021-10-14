package com.lin.demo.sign.lp.pipeline_04.pipeline;

import com.lin.demo.sign.lp.pipeline_04.model.Context;

/**
 * @author linpu
 * @dateTime 2021-10-14 10:28
 * @email im.linpu@qq.com
 * @description 管道
 */
public interface Invoker {

    void start();

    void shutDown();

    <T extends Context> T getContext();

}
