package com.lin.demo.pattern.strategy;

class ConcreteStrategyB extends AbstractStrategy {
    //算法的具体实现
    @Override
    public void algorithm() {
       //算法B
        System.out.println("算法B");
    }
}