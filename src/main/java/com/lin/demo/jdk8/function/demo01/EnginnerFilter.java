package com.lin.demo.jdk8.function.demo01;

@FunctionalInterface
public interface EnginnerFilter {
    boolean getMatchedEnginner(Enginner enginner);
}

