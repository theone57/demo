package com.all.lin.jdk8.stream8;

import com.all.lin.jdk8.function.demo01.Enginner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

/**
 * @author 小工匠
 * @version v1.0
 * @create 2020-05-16 9:13
 * @motto show me the code ,change the word
 * @blog https://artisan.blog.csdn.net/
 * @description
 **/

public class PredicateDemo {

    /**
     * 过滤符合规则的泛型类
     * @param list
     * @param predicate
     * @param <T>
     * @return
     */
    private static <T> List<T> filter(List<T> list , Predicate<T> predicate){
        List<T> targetList = new ArrayList<>();
        for (T t :list){
            if (predicate.test(t)){
                targetList.add(t);
            }
        }
        return  targetList;
    }

     public static void main(String[] args) {
         // 无装箱
         IntPredicate intPredicate = (int i) -> i % 2 == 0;
         intPredicate.test(1000);


         // 装箱
         Predicate<Integer> predicate = (Integer i) -> i % 2 == 0;
         predicate.test(1000);


        List<Enginner> enginnerList = Arrays.asList(new Enginner("Java", 18), new Enginner("GO", 20));
        List<Enginner> goEngineerList = filter(enginnerList, enginner -> enginner.getJob().equals("GO"));
        System.out.println(goEngineerList);
    }
}

