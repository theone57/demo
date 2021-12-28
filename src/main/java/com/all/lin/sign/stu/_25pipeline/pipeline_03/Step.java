package com.all.lin.sign.stu._25pipeline.pipeline_03;

public interface Step<T, U> {
    public U execute(T input);
}