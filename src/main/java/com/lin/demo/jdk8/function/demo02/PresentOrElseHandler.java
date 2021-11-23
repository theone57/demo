package com.lin.demo.jdk8.function.demo02;

import java.util.function.Consumer;

/**
 * 空值与非空值分支处理
 */
public interface PresentOrElseHandler<T extends Object> {

    /**
     * 值不为空时执行消费操作
     * 值为空时执行其他的操作
     *
     * @param action      值不为空时，执行的消费操作
     * @param emptyAction 值为空时，执行的操作
     * @return void
     **/
    void presentOrElseHandle(Consumer<? super T> action, Runnable emptyAction);


    /**
     * 参数为true或false时，分别进行不同的操作
     *
     * @param str
     * @return com.example.demo.func.BranchHandle
     **/
    static PresentOrElseHandler<?> isBlankOrNoBlank(String str) {
        return (consumer, runnable) -> {
            if (str == null || str.length() == 0) {
                runnable.run();
            } else {
                consumer.accept(str);
            }
        };
    }

    public static void main(String[] args) {
        PresentOrElseHandler<?> hello = isBlankOrNoBlank("hello");
        hello.presentOrElseHandle(System.out::println, () -> System.out.println("空字符串"));
    }
}