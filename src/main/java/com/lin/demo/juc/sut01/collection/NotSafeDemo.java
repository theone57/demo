package com.lin.demo.juc.sut01.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * ## 重现线程不安全：List
 * <p>
 * 首先以List作为演示对象，创建多个线程对List接口的常用实现类ArrayList进行add操作。
 * <p>
 * Vector和Synchronized的缺点：
 * <p>
 * ​		vector：**内存消耗比较大**，适合一次增量比较大的情况
 * <p>
 * ​		SynchronizedList：**迭代器涉及的代码没有加上线程同步代码**
 *
 * @author lin
 * @version v1.0
 * @program juc
 * @description
 */
@SuppressWarnings("all")
public class NotSafeDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
//        List<String> list = new Vector<>();
//        List<String> list = Collections.synchronizedList(new ArrayList<>());//CRUD加了synchronized;迭代器没有加锁

        //ArrayList在多个线程同时对其进行修改的时候，
        // 就会抛出**java.util.ConcurrentModificationException异常（并发修改异常）**，
        // 因为ArrayList的add及其他方法都是线程不安全的
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));//add()没有添加同步锁
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}

