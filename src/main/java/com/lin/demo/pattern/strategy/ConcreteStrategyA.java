package com.lin.demo.pattern.strategy;

class ConcreteStrategyA extends AbstractStrategy {
    //算法的具体实现
    @Override
    public void algorithm() {
       //算法A
        System.out.println("算法A");
    }
}