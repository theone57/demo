package com.lin.demo.sign.stu._25pipeline.pipeline.model;

import lombok.Data;

/**
 * @author linpu
 * @dateTime 2021-10-13 16:40
 * @email im.linpu@qq.com
 * @description
 */
@Data
public class FlowResult {
    private String code;
    private String message;

    public FlowResult() {
    }

    public FlowResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static FlowResult fail(String message) {
        return new FlowResult("0", message);
    }

    public static FlowResult ok() {
        return new FlowResult("1", "success");

    }
}

