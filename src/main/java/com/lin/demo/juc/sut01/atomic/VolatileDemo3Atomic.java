package com.lin.demo.juc.sut01.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Github:需要保证多用户提交时的版本安全问题
 * 1. 悲观锁: 可以给版本仓库枷锁,当用户需要操作时获取锁,获取道才能操作
 * 并发克隆时性能底
 * 2. 乐观锁: 每个仓库版本都有一个版本号
 * 当用户推送时可以检查用户推送的仓库的版本号,如果版本号一致,可以推送,推送成功更新版本号
 * 如果版本号不一致,必须先拉去最新的版本,然后合并再推送
 *
 * 不影响读
 *
 * 乐观锁机制: CAS Compare and swap 比较交换
 * @author lin
 * @version v1.0
 * @program juc
 * @description 乐观锁机制: CAS Compare and swap 比较交换
 * @date 2021-04-01 14:59
 */
class DataOne3{

//    int num = 0;
     private AtomicInteger num = new AtomicInteger(0);
    public  int incr(){
        return num.incrementAndGet();//在AtomicInteger的value值基础上+1
    }
}

public class VolatileDemo3Atomic {
    public static void main(String[] args) {
        DataOne3 dataOne = new DataOne3();
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                System.out.println(dataOne.incr());
            }).start();

        }

///        int i = 0;
//        i = ++i;
//        System.out.println(">>>>"+i);
    }
}

