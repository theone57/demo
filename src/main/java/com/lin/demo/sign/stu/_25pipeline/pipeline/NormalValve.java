package com.lin.demo.sign.stu._25pipeline.pipeline;

import com.lin.demo.sign.stu._25pipeline.pipeline.model.FlowResult;
import com.lin.demo.sign.stu._25pipeline.pipeline.model.PipeLineContext;

public class NormalValve implements Valve {
    protected Valve next = null;

    @Override
    public Valve getNext() {
        return next;
    }

    @Override
    public void setNext(Valve valve) {
        this.next = valve;
    }

    @Override
    public FlowResult invoke(PipeLineContext pipeLineContext) {
        return processContinue(pipeLineContext);
    }

    protected FlowResult processContinue(PipeLineContext pipeLineContext) {
        return next == null ? FlowResult.ok() : getNext().invoke(pipeLineContext);
    }
}