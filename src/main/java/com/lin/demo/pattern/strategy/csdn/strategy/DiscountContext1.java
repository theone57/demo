package com.lin.demo.pattern.strategy.csdn.strategy;

import java.math.BigDecimal;

/**
 * @author linpu
 * @dateTime 2021-07-06 16:46
 * @email im.linpu@qq.com
 * @phone 17602155862
 * @description
 */
public class DiscountContext1 {

    String memberType;

    private IDiscountStrategy discountStrategy;

    public DiscountContext1(String memberType) {
        this.memberType = memberType;
        this.discountStrategy = MemberDiscountFactory.getDiscountStrategy(memberType);
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public IDiscountStrategy getDiscountStrategy() {
        return discountStrategy;
    }

    public BigDecimal discount(BigDecimal price) {
        return discountStrategy.discount(price);
    }

}

