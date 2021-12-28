package com.all.lin.jdk8.function.demo01;

public class JavaEnginnerFilter implements EnginnerFilter {
    @Override
    public boolean getMatchedEnginner(Enginner enginner) {
        return "Java".equals(enginner.getJob());  // 仅筛选Java的
    }
}

