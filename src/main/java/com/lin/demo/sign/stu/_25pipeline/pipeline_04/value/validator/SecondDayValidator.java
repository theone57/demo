package com.lin.demo.sign.stu._25pipeline.pipeline_04.value.validator;

import com.lin.demo.sign.stu._25pipeline.pipeline_04.model.Context;
import com.lin.demo.sign.stu._25pipeline.pipeline_04.value.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class SecondDayValidator implements Value<Context> {

    @Override
    public boolean isEnable(Context context) {
        // todo isEnable
        return true;
    }

    @Override
    public String getName() {
        return "SecondDayValidator";
    }

    @Override
    public void validate(Context context) {
        // todo validate
        log.info("SecondDayValidator validate");

    }
}
