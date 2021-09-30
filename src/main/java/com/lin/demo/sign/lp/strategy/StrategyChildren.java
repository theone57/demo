package com.lin.demo.sign.lp.strategy;

/**
 * @author linpu
 * @dateTime 2021-07-05 18:12
 * @email im.linpu@qq.com
 * @phone 17602155862
 * @description
 */
public class StrategyChildren extends MovieTicketAbstractStrategy {
    @Override
    public double discount(double price) {
        System.out.println("儿童票：");
        if (price >= 20) {
            return price - 10;
        }
        return price;
    }
}

