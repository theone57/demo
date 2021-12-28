package com.all.lin.jdk8.stream8;

import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author linpu
 * @dateTime 2021-08-26 21:09
 * @email im.linpu@qq.com
 * @description 并行流
 */
public class ParallelDemo {


    public static Long adderByStream(Long limit) {
        return Stream.iterate(0L, i -> i + 1)//生成自然数无限流
                .limit(limit)// 限制
                .parallel() // 并行
                .reduce(0L, Long::sum); // 求和 归纳流
    }
    public static Long adderByLongStreamRangeClosed(Long limit){
        return LongStream.rangeClosed(1,limit)
                .reduce(0,Long::sum);
    }

    public static Long adderByNormal(Long limit) {
        Long result = 0L;
        for (int i = 0; i <= limit; i++) {
            result += i;
        }
        return result;
    }
}

