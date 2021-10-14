package com.lin.demo.sign.lp.pipeline_05;

public abstract class AbstractValve {
  /**
   * @author FeianLing
   * @date 2019/7/12
   * @desc 当前阀门需要的处理逻辑
   * @param [context] d.
   * @return void
   */
  abstract void execute(PipelineContext context);

  /**
   * @author FeianLing
   * @date 2019/7/12
   * @desc 调用阀门
   * @param [context]
   * @return void
   */
  void invoke(PipelineContext context) {
    execute(context);
    context.invokeNext();
  }
}

