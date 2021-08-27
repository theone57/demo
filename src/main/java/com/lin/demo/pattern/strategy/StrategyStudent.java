package com.lin.demo.pattern.strategy;

/**
 * @author linpu
 * @dateTime 2021-07-05 18:12
 * @email im.linpu@qq.com
 * @phone 17602155862
 * @description
 */
public class StrategyStudent extends MovieTicketAbstractStrategy {
    @Override
    public double discount(double price) {
        System.out.println("学生价格");
        return price * 0.8;
    }
}

