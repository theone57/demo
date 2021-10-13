package com.lin.demo.sign.lp.pipeline_03;

public interface Step<T, U> {
    public U execute(T input);
}