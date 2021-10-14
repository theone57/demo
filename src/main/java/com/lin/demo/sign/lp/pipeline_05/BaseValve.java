package com.lin.demo.sign.lp.pipeline_05;

import lombok.extern.slf4j.Slf4j;

//首先定义一个基础的阀门
@Slf4j
public class BaseValve extends AbstractValve {
  @Override
  void execute(PipelineContext context) {
    log.info("BaseValve > invoke > {}", context);
  }
}

