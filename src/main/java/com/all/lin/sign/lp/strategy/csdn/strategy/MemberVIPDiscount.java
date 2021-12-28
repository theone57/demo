package com.all.lin.sign.lp.strategy.csdn.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author linpu
 * @dateTime 2021-07-06 15:30
 * @email im.linpu@qq.com
 * @phone 17602155862
 * @description
 */
public class MemberVIPDiscount implements IDiscountStrategy {
    /**
     * 定义的抽象算法方法,来约束具体的算法实现方法
     *
     * @param price 价格
     * @return 实际价格
     */
    @Override
    public BigDecimal discount(BigDecimal price) {
        System.out.println("vip会员打9折！");
        price = price.multiply(new BigDecimal("0.9")).setScale(2, RoundingMode.HALF_UP);
        return price;
    }
}

