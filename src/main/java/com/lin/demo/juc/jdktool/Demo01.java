package com.lin.demo.juc.jdktool;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lin
 * @version v1.0
 * @description  juc
 */
public class Demo01 {
    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<>();
        //计数器
        int i = 0;

        boolean flag = true;

        while (flag) {
            i++;
            list.add(new byte[1024 * 1024]);
            try {
                Thread.sleep(70);
            } catch (InterruptedException e) {
                e.printStackTrace();
                flag = false;
            }
        }
    }

}

