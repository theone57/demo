package com.all.lin.sign.lp.strategy.csdn.bad;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PriceCenter {
    /**
     * 对原价进行折扣计算
     *
     * @param price      原价
     * @param memberType 会员类型
     * @return 计算折扣后价格
     */
    public BigDecimal discount(BigDecimal price, String memberType) {
        if ("普通会员".equals(memberType)) {
            System.out.println("普通会员没有折扣");
        } else if ("VIP会员".equals(memberType)) {
            System.out.println("vip会员打9折！");
            price = price.multiply(new BigDecimal("0.9")).setScale(2, RoundingMode.HALF_UP);
        } else if ("超级VIP会员".equals(memberType)) {
            System.out.println("超级VIP会员打5折！");
            price = price.multiply(new BigDecimal("0.7")).setScale(2, RoundingMode.HALF_UP);
        }
        return price;
    }




    /*----------------优化1----------------*/
/*下面看似让代码变得优雅了，但还是存在一些问题：
问题一：每新增一个会员类型的时候，首先要添加一个该种客户类型的报价算法方法，然后在方法discount中再加一个else if的分支，
这违反了设计原则之一的开闭原则（open-closed-principle）*/

    /**
     * 对原价进行折扣计算
     *
     * @param price      原价
     * @param memberType 会员类型
     * @return 计算折扣后价格
     */
    public BigDecimal discount2(BigDecimal price, String memberType) {
        if ("普通会员".equals(memberType)) {
            price = memberNormalDiscount(price);
        } else if ("VIP会员".equals(memberType)) {
            price = memberVIPDiscount(price);
        } else if ("超级VIP会员".equals(memberType)) {
            price = memberSuperVIPDiscount(price);
        }
        return price;
    }

    private BigDecimal memberNormalDiscount(BigDecimal price) {
        System.out.println("普通会员没有折扣");
        return price;
    }

    private BigDecimal memberVIPDiscount(BigDecimal price) {
        System.out.println("vip会员打9折！");
        price = price.multiply(new BigDecimal("0.9")).setScale(2, RoundingMode.HALF_UP);
        return price;
    }

    private BigDecimal memberSuperVIPDiscount(BigDecimal price) {
        System.out.println("超级VIP会员打8折！");
        price = price.multiply(new BigDecimal("0.8")).setScale(2, RoundingMode.HALF_UP);
        return price;
    }
}
