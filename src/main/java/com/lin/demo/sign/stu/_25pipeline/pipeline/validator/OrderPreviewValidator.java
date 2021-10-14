package com.lin.demo.sign.stu._25pipeline.pipeline.validator;

import com.lin.demo.sign.stu._25pipeline.pipeline.NormalValve;
import com.lin.demo.sign.stu._25pipeline.pipeline.model.FlowResult;
import com.lin.demo.sign.stu._25pipeline.pipeline.model.PipeLineContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author linpu
 * @dateTime 2021-10-13 17:20
 * @email im.linpu@qq.com
 * @description
 */
@Slf4j
@Component
public class OrderPreviewValidator extends NormalValve {
    @Override
    public FlowResult invoke(PipeLineContext pipeLineContext) {
        pipeLineContext.put("param2", "2");
        return processContinue(pipeLineContext);
    }
}

