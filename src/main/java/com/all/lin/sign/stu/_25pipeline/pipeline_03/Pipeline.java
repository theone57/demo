package com.all.lin.sign.stu._25pipeline.pipeline_03;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class Pipeline<IN, OUT> {
    private List<Step> pipelineSteps = new ArrayList<>();
    private Object firstStepInput = 100;

    //...
    public <A> Pipeline<OUT, A> add(Step<IN, A> step) {
        pipelineSteps.add(step);
        return (Pipeline<OUT, A>) this;
    }
    public void execute() {
        for (Step step : pipelineSteps) {
            Object out = step.execute(firstStepInput);
            firstStepInput = out;
        }
    }
}