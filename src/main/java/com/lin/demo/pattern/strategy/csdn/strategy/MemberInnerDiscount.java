package com.lin.demo.pattern.strategy.csdn.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author linpu
 * @dateTime 2021-07-06 15:39
 * @email im.linpu@qq.com
 * @phone 17602155862
 * @description 内部会员
 */
public class MemberInnerDiscount implements IDiscountStrategy {
    /**
     * 定义的抽象算法方法,来约束具体的算法实现方法
     *
     * @param price 价格
     * @return 实际价格
     */
    @Override
    public BigDecimal discount(BigDecimal price) {
        System.out.println("内部会员打5折！");
        price = price.multiply(new BigDecimal("0.5")).setScale(2, RoundingMode.HALF_UP);
        return price;
    }
}

