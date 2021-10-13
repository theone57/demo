package com.lin.demo.sign.lp.pipeline.validator;

import com.lin.demo.sign.lp.pipeline.NormalValve;
import com.lin.demo.sign.lp.pipeline.model.PipeLineContext;
import com.lin.demo.sign.lp.pipeline.model.FlowResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Validator 订单-临时订单前置参数校验
 */
@Slf4j
@Component
public class Validator extends NormalValve {
    @Override
    public FlowResult invoke(PipeLineContext pipeLineContext) {
        pipeLineContext.put("param", "1");
        return processContinue(pipeLineContext);
    }
}