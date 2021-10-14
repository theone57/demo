package com.lin.demo.sign.lp.pipeline_05;

public interface PipelineContext {
  /**
   * @author FeianLing
   * @date 2019/7/12
   * @desc 调用下一个阀门
   * @param []
   * @return void
   */
  void invokeNext();
}
