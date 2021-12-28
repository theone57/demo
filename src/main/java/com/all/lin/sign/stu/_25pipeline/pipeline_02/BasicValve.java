
package com.all.lin.sign.stu._25pipeline.pipeline_02;

public class BasicValve implements Valve {
    protected Valve next = null;
    @Override
    public Valve getNext() {
        return next;
    }

    @Override
    public void invoke(String handling) {
        handling=handling.replaceAll("aa","bb");
        System.out.println("aa---bb基础阀门处理完后：" + handling);
    }

    @Override
    public void setNext(Valve valve) {
        this.next = valve;
    }
}
