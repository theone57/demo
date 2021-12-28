package com.all.lin.juc.sut01.collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author lin
 * @version v1.0
 * @program juc
 * @description
 * @date 2021-03-31 09:38
 */
public class  NotSafeDemo3 {
    public static void main(String[] args) {
//        Map<String, String> map = new HashMap<>();
//        Map<String, String> map = Collections.synchronizedMap(new HashMap<>());
       Map<String, String> map = new ConcurrentHashMap<>();
//        for (int i = 0; i < 10; i++) {
//            new Thread(() -> {
//                map.put(String.valueOf(Thread.currentThread().getName()), UUID.randomUUID().toString().substring(0, 8));
//                System.out.println(map);
//            },String.valueOf(i)).start();
//        }

        Set<String> set = new HashSet<>();
//        Set<String> set = new CopyOnWriteArraySet<>();

            for (int i = 0; i < 20; i++) {
                new Thread(() -> {
                    set.add(String.valueOf(UUID.randomUUID().toString().substring(0, 8)));
                    System.out.println(set);
                },String.valueOf(i)).start();
            }



    }
}

