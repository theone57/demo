package com.lin.demo.sign.stu._25pipeline.pipeline_04.node;

import com.lin.demo.sign.stu._25pipeline.pipeline_04.model.Context;
import com.lin.demo.sign.stu._25pipeline.pipeline_04.value.Value;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;


@Slf4j
@Data
@NoArgsConstructor
public class ValidatorNode {

    private Value validator;

    private ValidatorNode nextNode;

    public ValidatorNode(Value validator) {
        this.validator = validator;
    }

    public void execute(Context context) {
        boolean hasNext = Objects.nonNull(nextNode);
        // 不可用，执行下一个
        if (!validator.isEnable(context)) {
            log.info("校验器 {} 条件不满足退出", validator.getName());
            if (hasNext) {
                nextNode.execute(context);
            }
        } else {
            validator.validate(context);
            if (hasNext) {
                nextNode.execute(context);
            }
        }
    }

}
