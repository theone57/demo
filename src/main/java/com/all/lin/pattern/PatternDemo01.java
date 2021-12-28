package com.all.lin.pattern;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author linpu
 * @dateTime 2021-08-27 15:55
 * @email im.linpu@qq.com
 * @description
 */
public class PatternDemo01 {
    public static void main(String[] args) {
//        Pattern p= Pattern.compile("\\w+");
//        p.pattern();//返回 \w+

        Pattern p = Pattern.compile("\\d+");
        String[] str = p.split("我的QQ是:456456我的电话是:0532214我的邮箱是:aaa@aaa.com");
        Matcher matcher = p.matcher("sdfs134sdfs");

            System.out.printf("group", matcher.group());
            System.out.printf("start", matcher.start());
            System.out.printf("end", matcher.end());
            System.out.printf("count", matcher.groupCount());


        Consumer<String> stringConsumer = (s) -> System.out.println(s.length());

        Arrays.asList("ab", "abc", "a", "abcd").stream().forEach(stringConsumer);
    }
}

