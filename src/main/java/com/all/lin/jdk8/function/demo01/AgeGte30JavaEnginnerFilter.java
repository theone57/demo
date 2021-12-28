package com.all.lin.jdk8.function.demo01;

public class AgeGte30JavaEnginnerFilter implements EnginnerFilter {
    @Override
    public boolean getMatchedEnginner(Enginner enginner) {
        return "Java".equals(enginner.getJob()) && enginner.getAge() >= 30;  // 筛选Java 并且年龄>=30的 
    }
}

