package com.lin.demo.jdk8.stream8;

import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author linpu
 * @dateTime 2021-08-26 21:09
 * @email im.linpu@qq.com
 * @description 并行流
 */
public class ParallelTest {


    public static void main(String[] args) {
        System.out.println(measureSumPerformance(ParallelTest::adderByNormal, 10000000) + "ms");
        System.out.println(measureSumPerformance(ParallelTest::adderByStream, 10000000) + "ms");
        System.out.println(measureSumPerformance(ParallelTest::adderByStreamParallel, 10000000) + "ms");
        System.out.println(measureSumPerformance(ParallelTest::adderByStreamParallel, 10) + "ms");
    }

    private static long measureSumPerformance(Function<Long, Long> adder, long limit) {
        long fastest = Long.MAX_VALUE;
        // 运行10次，找最快的一次
        for (int i = 0; i < 10; i++) {
            long startTimestamp = System.currentTimeMillis();
            adder.apply(limit);
            long duration = System.currentTimeMillis() - startTimestamp;
            if ((duration < fastest)) {
                fastest = duration;
            }
        }
        return fastest;
    }

    public static Long adderByStream(Long limit) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(limit)
                .reduce(0L, Long::sum);
    }

    public static Long adderByStreamParallel(Long limit) {
        return Stream.iterate(1L, i -> i + 1)
                .limit(limit)
                .parallel()
                .reduce(0L, Long::sum);
    }


    public static Long adderByNormal(Long limit) {
        Long result = 0L;

        for (int i = 0; i <= limit; i++) {
            result += i;
        }

        return result;
    }

}
    
