package com.lin.demo.pattern.strategy.csdn.strategy;

import java.math.BigDecimal;

/**
 * @author linpu
 * @dateTime 2021-07-06 15:34
 * @email im.linpu@qq.com
 * @phone 17602155862
 * @description 折扣上下文
 */
public class DiscountContext {
    /**
     * 策略
     */
    private IDiscountStrategy discountStrategy;

    public DiscountContext(IDiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public void setDiscountStrategy(IDiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    /**
     * 执行策略的方法
     */
    public BigDecimal discount(BigDecimal price) {
        return discountStrategy.discount(price);
    }
}

