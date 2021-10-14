package com.lin.demo.sign.stu._25pipeline.pipeline_04.value.validator;

import com.lin.demo.sign.stu._25pipeline.pipeline_04.model.Context;
import com.lin.demo.sign.stu._25pipeline.pipeline_04.value.Value;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@Setter(onMethod_ = {@Autowired})
public class FirstValidator implements Value<Context> {

    @Override
    public boolean isEnable(Context context) {
        // 首次提交才校验
        return true;
    }

    @Override
    public String getName() {
        return "FirstValidator";
    }

    @Override
    public void validate(Context context) {
        if (!isEnable(context)) {
            return;
        }
        // todo validate
        log.info("FirstValidator validate");
//        if(count > 0) {
//            throw new BusinessException("参数错误!");
//        }
    }
}
