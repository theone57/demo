package com.lin.demo.sign.stu._25pipeline.pipeline_05;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Valve3 extends AbstractValve {
  @Override
  void execute(PipelineContext context) {
    log.info("Valve3 > invoke > {}", context);
  }
}