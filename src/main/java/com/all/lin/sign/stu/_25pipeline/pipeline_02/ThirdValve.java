
package com.all.lin.sign.stu._25pipeline.pipeline_02;

public class ThirdValve implements Valve {
    protected Valve next = null;
    @Override
    public Valve getNext() {
        return next;
    }
    @Override
    public void invoke(String handling) {
        handling = handling.replaceAll("zz","yy");
        System.out.println("zz------yy  Third阀门处理完后：" + handling);
        getNext().invoke(handling);
    }
    @Override
    public void setNext(Valve valve) {
        this.next = valve;
    }
}
