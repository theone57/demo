
package com.lin.demo.sign.stu._25pipeline.pipeline_03;

import java.util.ArrayList;
import java.util.List;

public class DefaultPipeline {
    private List<Step> pipelineSteps = new ArrayList<>();
    private Object firstStepInput = 100;

    public void addStep(Step step) {
        pipelineSteps.add(step);
    }

    public void execute() {
        for (Step step : pipelineSteps) {
            Object out = step.execute(firstStepInput);
            firstStepInput = out;
        }
   }
}