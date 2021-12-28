package com.all.lin.sign.stu._25pipeline.pipeline_05;

import lombok.extern.slf4j.Slf4j;

//下面我们在定义几个阀门来测试
@Slf4j
public class Valve1 extends AbstractValve {
  @Override
  void execute(PipelineContext context) {
    log.info("Valve1 > invoke >{}", context);
  }
}