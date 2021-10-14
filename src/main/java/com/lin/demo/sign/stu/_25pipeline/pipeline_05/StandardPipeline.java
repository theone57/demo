package com.lin.demo.sign.stu._25pipeline.pipeline_05;

import java.util.ArrayList;
import java.util.List;

public class StandardPipeline implements Pipeline {
  /** 阀门集合 */
  private List<AbstractValve> valves;
  /** 基础阀门 */
  private AbstractValve bases;
  /** 管道流转的上下文，可根据业务需要在上下文中加入流转的数据 */
  private PipelineContext context;

  public StandardPipeline() {
    valves = new ArrayList<>();
    context = new StandardPipelineContext();
  }

  @Override
  public boolean setBases(AbstractValve valve) {
    bases = valve;
    return true;
  }

  @Override
  public boolean addValve(AbstractValve valve) {
    return valves.add(valve);
  }

  @Override
  public boolean removeValve(AbstractValve valve) {
    if (valves.contains(valve)) {
      return valves.remove(valve);
    }
    return true;
  }

  @Override
  public void invoke() {
    context.invokeNext();
  }

  /** 定义一个管道上下文内部类 实现管道上下文接口 */
  private class StandardPipelineContext implements PipelineContext {
    int index = 0;

    @Override
    public void invokeNext() {
      if (!valves.isEmpty() && index < valves.size()) {
        valves.get(index++).invoke(context);
      } else {
        bases.execute(context);
      }
    }
  }
}

