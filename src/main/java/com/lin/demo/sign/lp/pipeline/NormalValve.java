package com.lin.demo.sign.lp.pipeline;

import com.lin.demo.sign.lp.pipeline.model.FlowResult;
import com.lin.demo.sign.lp.pipeline.model.PipeLineContext;

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