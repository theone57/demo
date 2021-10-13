package com.lin.demo.sign.lp.pipeline_03;

public class StepThree implements Step<Integer, String> {
    @Override
    public String execute(Integer input) {
        return "The final amount is " + input;
    }
}