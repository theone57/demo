
package com.all.lin.sign.stu._25pipeline.pipeline_05;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Valve2 extends AbstractValve {
  @Override
  void execute(PipelineContext context) {
    log.info("Valve2 > invoke > {}", context);
  }
}