package com.lin.demo.sign.lp.pipeline.process;

import com.lin.demo.sign.lp.pipeline.NormalValve;
import com.lin.demo.sign.lp.pipeline.model.FlowResult;
import com.lin.demo.sign.lp.pipeline.model.PipeLineContext;
import org.springframework.stereotype.Service;

/**
 * @author linpu
 * @dateTime 2021-10-14 10:18
 * @email im.linpu@qq.com
 * @description
 */
@Service
public class Processor extends NormalValve {
    @Override
    public FlowResult invoke(PipeLineContext pipeLineContext) {
        pipeLineContext.put("param3", "3");
        return processContinue(pipeLineContext);
    }
}

