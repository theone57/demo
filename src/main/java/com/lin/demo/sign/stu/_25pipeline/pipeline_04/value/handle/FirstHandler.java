package com.lin.demo.sign.stu._25pipeline.pipeline_04.value.handle;

import com.lin.demo.sign.stu._25pipeline.pipeline_04.model.Context;
import com.lin.demo.sign.stu._25pipeline.pipeline_04.value.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FirstHandler implements Value<Context> {


    @Override
    public boolean isAsync() {
        return false;
    }

    @Override
    public boolean isEnable(Context context) {
        // todo isEnable
        return true;
    }

    @Override
    public boolean handle(Context context) {
        // todo handle
        log.info("FirstHandler handle");
        return true;
    }

    @Override
    public String getName() {
        return "first handle";
    }
}
