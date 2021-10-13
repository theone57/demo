package com.lin.demo.sign.lp.pipeline;

import com.lin.demo.sign.lp.pipeline.model.FlowResult;
import com.lin.demo.sign.lp.pipeline.model.PipeLineContext;
import org.springframework.stereotype.Component;

/**
 * NormalPipeLine PipeLine接口通用实现
 */
@Component
public class NormalPipeLine implements PipeLine {
    private Valve head = null;
    private Valve next = null;

    @Override
    public void addValve(Valve valve) {
        if (head == null) {
            head = valve;
            valve.setNext(next);
        } else {
            Valve current = head;
            while (current != null) {
                if (current.getNext() == next) {
                    current.setNext(valve);
                    valve.setNext(next);
                    break;
                }
                current = current.getNext();
            }
        }
    }


    @Override
    public FlowResult start(PipeLineContext pipeLineContext) {
        if (pipeLineContext == null) {
            return FlowResult.fail("pipeLineContext should be not null!");
        }
        if (head == null) {
            return FlowResult.fail("there's no valve in current pipeLine!");
        }
        return head.invoke(pipeLineContext);
    }
}
