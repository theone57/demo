
package com.lin.demo.sign.stu._25pipeline.pipeline_02;

public class SecondValve implements Valve {
    protected Valve next = null;
    @Override
    public Valve getNext() {
        return next;
    }

    @Override
    public void invoke(String handling) {
        handling = handling.replaceAll("11","22");
        System.out.println("11----22  Second阀门处理完后：" + handling);
        getNext().invoke(handling);
    }

    @Override
    public void setNext(Valve valve) {
        this.next = valve;
    }
}
