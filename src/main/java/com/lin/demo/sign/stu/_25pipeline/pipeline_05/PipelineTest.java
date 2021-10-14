package com.lin.demo.sign.stu._25pipeline.pipeline_05;

public class PipelineTest {
  public static void main(String[] args) {
    StandardPipeline pipeline = new StandardPipeline();
    BaseValve baseValve = new BaseValve();
    Valve1 valve1 = new Valve1();
    Valve2 valve2 = new Valve2();
    Valve3 valve3 = new Valve3();
    pipeline.setBases(baseValve);
    pipeline.addValve(valve1);
    pipeline.addValve(valve3);
    pipeline.addValve(valve2);
    pipeline.invoke();
  }
}

