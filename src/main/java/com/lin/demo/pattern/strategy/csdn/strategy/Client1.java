package com.lin.demo.pattern.strategy.csdn.strategy;

import java.math.BigDecimal;

public class Client1 {
    public static void main(String[] args) {
        //这里使用最简单方式表示类型
        String memberType = "VIP";
        //原价
        BigDecimal price = new BigDecimal(1000);
        //使用工厂获取具体策略
        IDiscountStrategy iDiscountStrategy = MemberDiscountFactory.getDiscountStrategy(memberType);
        DiscountContext discountContext = new DiscountContext(iDiscountStrategy);
        BigDecimal result = discountContext.discount(price);
        System.out.println("打折后的价格为"+result);
    }
}
