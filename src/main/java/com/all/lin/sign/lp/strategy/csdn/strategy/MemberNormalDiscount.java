package com.all.lin.sign.lp.strategy.csdn.strategy;

import java.math.BigDecimal;

/**
 * @author linpu
 * @dateTime 2021-07-06 15:28
 * @email im.linpu@qq.com
 * @phone 17602155862
 * @description
 */
public class MemberNormalDiscount implements IDiscountStrategy{
    /**
     * 普通会员
     *
     * @param price 价格
     * @return 实际价格
     */
    @Override
    public BigDecimal discount(BigDecimal price) {
        System.out.println("普通会员没有折扣");
        return price;
    }
}

