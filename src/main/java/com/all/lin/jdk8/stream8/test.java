package com.all.lin.jdk8.stream8;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author: bingju
 * @date: 2021-08-25 5:10 下午
 * /**
 * /fadf/fadf/1/fad/1
 * /fadf/fadf/1/fad/2
 * /fadf/fadf/1/fad/3
 * /fadf/fadf/2/fad/1
 * /fadf/fadf/2/fad/2
 * /fadf/fadf/2/fad/3
 * /fadf/fadf/3/fad/1
 * /fadf/fadf/3/fad/2
 * /fadf/fadf/3/fad/3
 **/
public class test {

    public static void main(String[] args) {

        String patch = "/fadf/fadf/#[1-3]/fad/#[1,2,3]";
        List<String> match = match(patch);
        System.out.println(match.size());
        match.forEach(System.out::println);

    }

    public static List<String> match(String patch) {
        try {
            String rex = "#\\[.*?\\]";
            Pattern pattern = Pattern.compile(rex);
            Matcher matcher = pattern.matcher(patch);
            List<String> results = new ArrayList<>();
            List<String> tempList = Lists.newArrayList();

            String s1 = patch.replaceAll(rex, "%s");
            tempList.add(s1);

            while (matcher.find()) {
                results.clear();
                results.addAll(tempList);
                tempList.clear();

                String s = matcher.group().replaceAll("#\\[|\\]", "");
                List<String> strings = flatMapStr(s);
                results.forEach(result -> strings.forEach(it -> tempList.add(result.replaceFirst("%s", it))));

            }
            return tempList;
        } catch (Exception ignore) {
            //TODO
        }
        return Lists.newArrayList();
    }

    public static List<String> flatMapStr(String patch) {
        return Stream.of(patch).flatMap(it -> flatMapRule(it, patch)).collect(Collectors.toList());
    }

    //规则在这里修改
    public static Stream<String> flatMapRule(String str, String patch) {
        if (str.contains("-")) {
            String[] split1 = patch.split("-");
            return IntStream.rangeClosed(Integer.parseInt(split1[0]), Integer.parseInt(split1[1]))
                    .mapToObj(String::valueOf);
        } else {
            String[] split1 = patch.split(",");
            return Stream.of(split1);
        }
    }
}
