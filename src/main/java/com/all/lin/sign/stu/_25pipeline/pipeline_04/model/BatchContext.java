package com.all.lin.sign.stu._25pipeline.pipeline_04.model;

import lombok.Data;

/**
 * @author linpu
 * @dateTime 2021-10-14 11:40
 * @email im.linpu@qq.com
 * @description
 */
@Data
public class BatchContext extends Context {
    public boolean isFirstSave() {
        return true;
    }
}

