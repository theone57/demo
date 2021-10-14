package com.lin.demo.sign.stu._25pipeline.pipeline_03;

public class StepThree implements Step<Integer, String> {
    @Override
    public String execute(Integer input) {
        return "The final amount is " + input;
    }
}