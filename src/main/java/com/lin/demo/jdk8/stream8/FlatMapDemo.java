package com.lin.demo.jdk8.stream8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author linpu
 * @dateTime 2021-08-27 15:31
 * @email im.linpu@qq.com
 * @description
 */
public class FlatMapDemo {
    public static void main(String[] args) {
        Stream<String> stringStream = new ArrayList<String>().stream().map(x -> x);//返回一个
        Stream<String> stringStream1 = new ArrayList<String>().stream().flatMap(x -> Arrays.asList(x.split(" ")).stream());//返回一个流,也就是多个值

        String[] strs = {"java8", "is", "easy", "to", "use"};
        Arrays.stream(strs).sorted(String::compareTo);
        List<String[]> distinctStrs = Arrays.stream(strs)
                .map(str -> str.split(""))  // 映射成为Stream<String[]>
                .distinct()
                .collect(Collectors.toList());
        distinctStrs.forEach(r->{
            System.out.println(Arrays.asList(r));
        });

        List<String> distinctStrs2 = Arrays.stream(strs)
                .map(str -> str.split(""))  // 映射成为Stream<String[]>
                .flatMap(Arrays::stream)  // 扁平化为Stream<String>
                .distinct()
                .collect(Collectors.toList());
        System.out.println("distinctStrs2 = " + distinctStrs2);
    }
}

