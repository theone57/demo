package com.lin.demo.pattern.strategy.csdn.strategy;
/**
 * 策略接口
 */



import java.math.BigDecimal;

public interface IDiscountStrategy {
    /**
     * 定义的抽象算法方法,来约束具体的算法实现方法
     *
     * @param price 价格
     * @return 实际价格
     */
    BigDecimal discount(BigDecimal price);
}
