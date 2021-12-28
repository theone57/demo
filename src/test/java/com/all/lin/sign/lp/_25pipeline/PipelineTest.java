package com.all.lin.sign.lp._25pipeline;

import com.alibaba.fastjson.JSON;
import com.all.DemoApplication;
import com.all.lin.sign.stu._25pipeline.pipeline.NormalPipeLine;
import com.all.lin.sign.stu._25pipeline.pipeline.model.FlowResult;
import com.all.lin.sign.stu._25pipeline.pipeline.model.PipeLineContext;
import com.all.lin.sign.stu._25pipeline.pipeline.process.Processor;
import com.all.lin.sign.stu._25pipeline.pipeline.validator.OrderPreviewValidator;
import com.all.lin.sign.stu._25pipeline.pipeline.validator.Validator;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class PipelineTest {

    @Autowired
    private NormalPipeLine normalPipeLine;
    @Autowired
    private Validator validator;
    @Autowired
    private OrderPreviewValidator orderPreviewValidator;
    @Autowired
    private Processor processor;

    @Test
    public void testUserController() {
        // 定义上下文
        PipeLineContext pipeLineContext = new PipeLineContext(0);
        pipeLineContext.put("index", "0");
        // 增加阀门
        normalPipeLine.addValve(validator); // 参数校验阀门
        normalPipeLine.addValve(orderPreviewValidator); // 业务校验阀门
        normalPipeLine.addValve(processor); // 业务处理阀门
        // 管道执行
        FlowResult flowResult = normalPipeLine.start(pipeLineContext);
        log.info(JSON.toJSONString(flowResult));
    }

}
