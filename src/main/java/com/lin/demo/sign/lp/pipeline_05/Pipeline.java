package com.lin.demo.sign.lp.pipeline_05;

/**
 * @author FeianLing
 * @date 2019/7/12
 */
public interface Pipeline {
  /**
   * @author FeianLing
   * @date 2019/7/12
   * @desc 设计基础阀门，一个管道必须基本有一个基本阀门
   * @param [bases]
   * @return void
   */
  boolean setBases(AbstractValve valve);
  /**
   * @author FeianLing
   * @date 2019/7/12
   * @desc 添加阀门信息
   * @param [valve]
   * @return void
   */
  boolean addValve(AbstractValve valve);
  /**
   * @author FeianLing
   * @date 2019/7/12
   * @desc 移除管道中的阀门
   * @param [valve]
   * @return void
   */
  boolean removeValve(AbstractValve valve);
  /**
   * @author FeianLing
   * @date 2019/7/12
   * @desc 调用管道处理数据
   * @param []
   * @return void
   */
  void invoke();
}
