package com.all.lin.sign.stu._25pipeline.pipeline_02;

public interface Valve {
    public Valve getNext();
    public void setNext(Valve valve);
    public void invoke(String handling);
}
