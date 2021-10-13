package com.lin.demo.sign.lp.pipeline_02;

public interface Pipeline {
    public Valve getFirst();
    public Valve getBasic();

    public void setBasic(Valve valve);
    public void addValve(Valve valve);
}
