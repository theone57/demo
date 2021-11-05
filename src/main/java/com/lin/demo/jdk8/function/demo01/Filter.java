package com.lin.demo.jdk8.function.demo01;

@FunctionalInterface
public interface Filter<T> {
    boolean filter(T t);
}

