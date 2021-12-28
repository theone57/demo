package com.all.lin.sign.lp.strategy.csdn.strategy;

import java.math.BigDecimal;

public class Client {
    public static void main(String[] args) {
        /*----------普通会员---------*/
        BigDecimal price1 = new BigDecimal(1000);
        MemberNormalDiscount memberNormalDiscount = new MemberNormalDiscount();
        DiscountContext discountContext = new DiscountContext(memberNormalDiscount);
        price1 = discountContext.discount(price1);
        System.out.println(price1);

        /*----------超级会员---------*/
        BigDecimal price2 = new BigDecimal(1000);
        discountContext.setDiscountStrategy(new MemberSuperVIPDiscount());
        price2 = discountContext.discount(price2);
        System.out.println(price2);
    }
}
