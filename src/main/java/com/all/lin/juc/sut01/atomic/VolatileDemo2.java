package com.all.lin.juc.sut01.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lin
 * @version v1.0
 * @program juc
 * @description 测试CAS 的实现 AtomicXX类
 * @date 2021-04-01 15:35
 */
public class VolatileDemo2 {
    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger(2);
        boolean b = integer.compareAndSet(2, 4);
        System.out.println("更新成功?b = " + b);
        System.out.println(integer.get());
        System.out.println(integer.compareAndSet(4, 5));
        boolean x = integer.weakCompareAndSet(5, 6);
        System.out.println(x);
    }
}

