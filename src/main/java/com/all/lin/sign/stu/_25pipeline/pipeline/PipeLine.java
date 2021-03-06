package com.all.lin.sign.stu._25pipeline.pipeline;


import com.all.lin.sign.stu._25pipeline.pipeline.model.FlowResult;
import com.all.lin.sign.stu._25pipeline.pipeline.model.PipeLineContext;

/**
 * PipeLine 管道接口，包括添加阀门方法以及开启管道方法
 * {@link PipelineTest}
 */
public interface PipeLine {
    /**
     * 添加阀门
     * @param valve 阀门
     */
    void addValve(Valve valve);

    /**
     * 开启管道
     * @param pipeLineContext 管道上下文
     * @return FlowResult
     */
    FlowResult start(PipeLineContext pipeLineContext);
}