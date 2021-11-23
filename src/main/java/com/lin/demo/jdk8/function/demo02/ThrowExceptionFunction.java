package com.lin.demo.jdk8.function.demo02;

/**
 * 抛异常接口
 **/
@FunctionalInterface
public interface ThrowExceptionFunction {
    public static void main(String[] args) {
        isTure(true).throwMessage("xxx");

    }
    /**
     * 抛出异常信息
     *
     * @param message 异常信息
     * @return void
     **/
    void throwMessage(String message);

    /**
     *  如果参数为true抛出异常
     *
     * @param b
     * @return com.example.demo.func.ThrowExceptionFunction
     **/
    static ThrowExceptionFunction isTure(boolean b){
        return (errorMessage) -> {
            if (b){
                throw new RuntimeException(errorMessage);
            }
        };
    }
}
