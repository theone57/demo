package com.lin.demo.sign.stu._25pipeline.pipeline_03;

public class StepOne implements Step<Integer, Integer> {
    @Override
    public Integer execute(Integer input) {
        return input + 100;
    }
}



