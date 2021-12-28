package com.all.lin.sign.lp.strategy.csdn.strategy;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 工厂方法只是一种解决方式，其主要的思路就是将各个策略放到容器中去来方便获取。大家可以发散思路，比如枚举，反射等等
 *
 * @author linpu
 * @dateTime 2021-07-06 15:43
 * @email im.linpu@qq.com
 * @phone 17602155862
 * @description 会员折扣策略工厂
 */
public class MemberDiscountFactory {

    private static final Map<String, IDiscountStrategy> MAP = new ConcurrentHashMap<>();

    static {
        MAP.put("normal", new MemberNormalDiscount());
        MAP.put("VIP", new MemberSuperVIPDiscount());
        MAP.put("superVIP", new MemberSuperVIPDiscount());
    }

    public static void main(String[] args) {

    }

    /**
     * 获取具体策略类型
     *
     * @param memberType
     * @return
     */
    public static IDiscountStrategy getDiscountStrategy(String memberType) {
        return MAP.get(memberType);
    }
}

