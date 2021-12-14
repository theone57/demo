
package com.lin.demo.collectin.bitmap;

//去除重复并排序

import java.util.Arrays;
import java.util.BitSet;
import java.util.Random;

/**
 * @author Gavenyeah
 * @date Time: 2016年4月15日下午9:20:21
 * @des:
 */
public class BitMap {
    int ARRNUM = 800;
    int LEN_INT = 32;
    int mmax = 9999;
    int mmin = 1000;
    int N = mmax - mmin + 1;// 9000

    public static void main(String args[]) {
        new BitMap().findDuplicate();
        new BitMap().findDup_jdk();
    }

    public void findDup_jdk() {
        System.out.println("*******调用JDK中的库方法--开始********");
        BitSet bitArray = new BitSet(N);
        int[] array = getArray(ARRNUM); // 800 随机数
        for (int i = 0; i < ARRNUM; i++) {
            bitArray.set(array[i] - mmin);
        }

        int count = 0;

        for (int j = 0; j < bitArray.length(); j++) {
            if (bitArray.get(j)) {
                System.out.print(j + mmin + " ");
                count++;
            }
        }
        System.out.println();
        System.out.println("排序后的数组大小为：" + count);
        System.out.println("*******调用JDK中的库方法--结束********");
    }

    public void findDuplicate() {
        int[] array = getArray(ARRNUM);
        int[] bitArray = setBit(array);
        printBitArray(bitArray);
    }

    public void printBitArray(int[] bitArray) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (getBit(bitArray, i) != 0) {
                count++;
                System.out.print(i + mmin + "\t");
            }
        }
        System.out.println();
        System.out.println("去重排序后的数组大小为：" + count);
    }

    public int getBit(int[] bitArray, int k) {// 1 右移 k % 32位 与上 数组下标为 k/32 位置的值
        return bitArray[k / LEN_INT] & (1 << (k % LEN_INT));
    }

    public int[] setBit(int[] array) {
        // 首先取得数组位置下标 i/32, 然后 或上 在该位置int类型数值的bit位：i % 32
        int m = array.length;
        int bit_arr_len = N / LEN_INT + 1;
        int[] bitArray = new int[bit_arr_len];
        for (int i = 0; i < m; i++) {
            int num = array[i] - mmin; // 3218
            // 3218/32 = 0 |
            bitArray[num / LEN_INT] |= (1 << (num % LEN_INT));
        }
        return bitArray;
    }

    public int[] getArray(int ARRNUM) {

        @SuppressWarnings("unused")
        int[] array1 = {1000, 1002, 1032, 1033, 6543, 9999, 1033, 1000};

        int[] array = new int[ARRNUM];// 800
        System.out.println("数组大小：" + ARRNUM);
        Random r = new Random();
        for (int i = 0; i < ARRNUM; i++) {
            array[i] = r.nextInt(N) + mmin; // + 1000
        }

        System.out.println(Arrays.toString(array));
        return array;
    }
}


/**
 * 大数据处理算法一，bitmap算法
 *
 * @author JYC506
 */
class BitMapTwo {

    byte[] tem;

    BitMapTwo(int length) {
        this.tem = new byte[length];
    }

    public void add(int num) {
        if (num < tem.length) {
            if (tem[num] != 1) {
                tem[num] = 1;
            }
        }
    }

    public boolean contain(int num) {
        if (num < tem.length) {
            if (tem[num] == 1) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        /*运行前内存*/
        long beforeMemory = Runtime.getRuntime().totalMemory();
        long start1 = System.currentTimeMillis();
        BitSet set = new BitSet(2000000000);
        for (int i = 0; i < 2000000000; i++) {
            /*假设898989这个数不在20亿个数里面*/
            if (i != 898989) {
                set.set(i, true);
            }
        }
        /*创建20亿个数后所占内存*/
        long afterMemory = Runtime.getRuntime().totalMemory();
        long end1 = System.currentTimeMillis();
        System.out.println("总共内存使用:" + (afterMemory - beforeMemory) / 1024 / 1024 + "MB");
        System.out.println("存入内存耗时:" + (end1 - start1) + "毫秒");
        long start2 = System.currentTimeMillis();
        boolean isExit1 = set.get(898989);
        boolean isExit2 = set.get(900000);

        long end2 = System.currentTimeMillis();
        /*输出在20亿个数中判断898989是否包含在里面*/
        System.out.println(isExit1);
        System.out.println("20个亿中" + (isExit1 ? "包含" : "不包含") + 898989);
        System.out.println("20个亿中" + (isExit2 ? "包含" : "不包含") + 900000);
        System.out.println("查询用时:" + (end2 - start2) + "毫秒");
    }

}



