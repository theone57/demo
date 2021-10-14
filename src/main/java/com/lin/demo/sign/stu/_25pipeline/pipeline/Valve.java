package com.lin.demo.sign.stu._25pipeline.pipeline;

import com.lin.demo.sign.stu._25pipeline.pipeline.model.FlowResult;
import com.lin.demo.sign.stu._25pipeline.pipeline.model.PipeLineContext;

/**
 * Valve 阀门接口，阀门都需实现该接口或者该接口的扩展接口
 */
public interface Valve {
    /**
     * 获取下一个阀门
     * @return Valve 阀门
     */
    Valve getNext();

    /**
     * 设置下一个阀门
     * @param valve 阀门
     */
    void setNext(Valve valve);

    /**
     * 执行管道
     * @param pipeLineContext 管道上下文
     * @return FlowResult
     */
    FlowResult invoke(PipeLineContext pipeLineContext);
}
