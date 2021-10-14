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
public class DefaultNode {
    private Value value;

    private DefaultNode nextNode;

    public DefaultNode(Value value) {
        this.value = value;
    }

    public void execute(Context originContext) {
        boolean success = false;
        boolean hasNext = Objects.nonNull(nextNode);
        Context context = originContext;
        // 如果是批量处理器，取批量context
        if (value.isBatchCast()) {
            context = originContext.getBatchBo().getBatchContext();
        }
        // 不可用，执行下一个
        if (!value.isEnable(context)) {
            log.info(" 阀门 {} 条件不满足退出", value.getName());
            if (hasNext) {
                // 重新赋值
                context = originContext;
                nextNode.execute(context);
            } else {
                return;
            }
            // 可用，开始处理
        } else {
            if (value.isAsync()) {
                // 异步处理
            } else {
                success = value.handle(context);
            }
            if (success && hasNext) {
                // 重新赋值
                context = originContext;
                nextNode.execute(context);
            }
        }
    }

}
