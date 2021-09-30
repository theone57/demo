package com.lin.demo.clone;

/**
 * @author linpu
 * @dateTime 2021-09-27 09:14
 * @email im.linpu@qq.com
 * @description
 */
public class Computer implements Cloneable{
    @Override
    public Computer clone() {
        try {
            return (Computer) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }

    }
}

