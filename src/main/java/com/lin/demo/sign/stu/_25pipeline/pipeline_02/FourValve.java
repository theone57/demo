package com.lin.demo.sign.stu._25pipeline.pipeline_02;

public class FourValve implements Valve {
    protected Valve next = null;
    @Override
    public Valve getNext() {
        return next;
    }
    @Override
    public void invoke(String handling) {
        handling = handling.replaceAll("2222","1111");
        System.out.println("2222------1111  Four阀门处理完后：" + handling);
        getNext().invoke(handling);
    }
    @Override
    public void setNext(Valve valve) {
        this.next = valve;
    }
}
