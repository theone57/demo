package com.lin.demo.sign.lp.pipeline;

import com.alibaba.fastjson.JSON;
import com.lin.demo.DemoApplication;
import com.lin.demo.sign.lp.pipeline.model.FlowResult;
import com.lin.demo.sign.lp.pipeline.model.PipeLineContext;
import com.lin.demo.sign.lp.pipeline.validator.OrderPreviewValidator;
import com.lin.demo.sign.lp.pipeline.validator.Validator;
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
//	@Autowired
//	private Processor processor;

    @Test
    public void testUserController() {
        // 定义上下文
        PipeLineContext pipeLineContext = new PipeLineContext(0);
        pipeLineContext.put("index", "0");
        // 增加阀门
        normalPipeLine.addValve(validator); // 参数校验阀门
        normalPipeLine.addValve(orderPreviewValidator); // 业务校验阀门
//		normalPipeLine.addValve(processor); // 业务处理阀门
        // 管道执行
        FlowResult flowResult = normalPipeLine.start(pipeLineContext);
        log.info(JSON.toJSONString(flowResult));
    }

}
