package com.all.lin.sign.stu._25pipeline.pipeline_02;

public interface Pipeline {
    public Valve getFirst();
    public Valve getBasic();

    public void setBasic(Valve valve);
    public void addValve(Valve valve);
}
