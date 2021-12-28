package com.all.lin.jdk8.function.demo02;

/**
 * 分支处理接口
 *
 * @author alex
 */
@FunctionalInterface
public interface BranchHandle {

    /**
     * 分支操作
     *
     * @param trueHandle  为true时要进行的操作
     * @param falseHandle 为false时要进行的操作
     * @return void
     **/
    void trueOrFalseHandle(Runnable trueHandle, Runnable falseHandle);

    /**
     * 参数为true或false时，分别进行不同的操作
     *
     * @param b
     * @return com.example.demo.func.BranchHandle
     **/
    static BranchHandle isTureOrFalse(boolean b) {

        return (trueHandle, falseHandle) -> {
            if (b) {
                trueHandle.run();
            } else {
                falseHandle.run();
            }
        };
    }

    public static void main(String[] args) {
        isTureOrFalse(true).trueOrFalseHandle(
                () -> System.out.println("is true print")
                , () -> System.out.println("is false print"));
    }
}