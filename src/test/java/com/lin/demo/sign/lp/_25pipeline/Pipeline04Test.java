package com.lin.demo.sign.lp._25pipeline;

import com.lin.demo.DemoApplication;
import com.lin.demo.sign.stu._25pipeline.pipeline_04.model.Context;
import com.lin.demo.sign.stu._25pipeline.pipeline_04.pipeline.Pipeline;
import com.lin.demo.sign.stu._25pipeline.pipeline_04.pipeline.ValidatePipeLine;
import com.lin.demo.sign.stu._25pipeline.pipeline_04.value.handle.FirstHandler;
import com.lin.demo.sign.stu._25pipeline.pipeline_04.value.handle.SecondHandler;
import com.lin.demo.sign.stu._25pipeline.pipeline_04.value.validator.FirstValidator;
import com.lin.demo.sign.stu._25pipeline.pipeline_04.value.validator.SecondDayValidator;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author linpu
 * @dateTime 2021-10-14 13:59
 * @email im.linpu@qq.com
 * @description
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class Pipeline04Test {
    @Autowired
    private FirstValidator firstValidator;
    @Autowired
    private SecondDayValidator secondDayValidator;
    @Autowired
    private FirstHandler firstHandler;
    @Autowired
    private SecondHandler secondHandler;


    @Test
    public void test() {
        Context context = new Context();
//        Pipeline defaultPipeLine = new DefaultPipeLine(context);
//        defaultPipeLine.addLast(firstValidator);
//        defaultPipeLine.addLast(secondDayValidator);
//        defaultPipeLine.addLast(firstHandler);
//        defaultPipeLine.addLast(secondHandler);
//        defaultPipeLine.start();

        Pipeline validatePipeLine = new ValidatePipeLine(context);
        validatePipeLine.addLast(firstValidator);
        validatePipeLine.addLast(secondDayValidator);
        validatePipeLine.addLast(firstHandler);
        validatePipeLine.addLast(secondHandler);
        validatePipeLine.start();
    }
}

