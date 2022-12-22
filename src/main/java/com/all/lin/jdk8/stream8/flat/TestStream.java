package com.all.lin.jdk8.stream8.flat;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestStream {
    private static List<Boy> boys = new ArrayList<>();
    private static List<Student> stus = new ArrayList<>();
    static {


        boys.add(new Boy("tom", 12,
                Arrays.asList(
                        new Toy("飞机", 9.1),
                        new Toy("坦克", 7.0),
                        new Toy("小熊", 14.0))));

        boys.add(new Boy("tony", 19,
                Arrays.asList(
                        new Toy("玩具枪", 13.1),
                        new Toy("小汽车", 12.2))));


        stus.add(new Student("tom", "衡水中学", "尖子班"));
        stus.add(new Student("jerry", "黄冈中学", "普通班"));
        stus.add(new Student("dom", "衡水一中", "文科班"));
    }

    public static void main(String[] args) {

        //此时是处理第二个循环
        List<Toy> toys = boys.stream().flatMap(
                        //将流中元素的数据再次展开
                        b -> b.getToys().stream()).
                collect(Collectors.toList());
        System.out.println(JSON.toJSONString(toys));
//        等效于
        //因为是1:n的关系，所以最终会产生n记录
        List<Toy> tls = new ArrayList<>();
        for (Boy boy : boys) {
            for (Toy toy : boy.getToys()) {
                tls.add(toy);
            }
        }
        //打印数据
        System.out.println(JSON.toJSONString(tls));
    }

    public static void main2(String[] args) {
        List<String> collect1 = boys.stream().flatMap(b -> b.getToys().stream().
                filter(t -> t.getPrice() > 12).
                map(t -> {
                    //即可以操作外层元素也可以操作内层元素
                    return b.getName() + ":" + t.getName();
                })).collect(Collectors.toList());

        System.out.println(JSON.toJSONString(collect1));
        //等效于：
        List<String> tls1 = new ArrayList<>();
        for (Boy boy : boys) {
            for (Toy toy : boy.getToys()) {
                if (toy.getPrice() > 12) {
                    tls1.add(boy.getName() + ":" + toy.getName());
                }
            }
        }
        System.out.println(JSON.toJSONString(tls1));
    }
}
