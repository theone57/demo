package com.lin.demo.pattern.strategy;

class Context {
private AbstractStrategy strategy; //维持一个对抽象策略类的引用
 
    public void setStrategy(AbstractStrategy strategy) {
        this.strategy= strategy;
    }
 
    //调用策略类中的算法
    public void algorithm() {
        strategy.algorithm();
    }

    public static void main(String[] args) {
        Context context = new Context();
        AbstractStrategy strategy;
        strategy = new ConcreteStrategyA(); //可在运行时指定类型
        context.setStrategy(strategy);
        context.algorithm();
    }
}