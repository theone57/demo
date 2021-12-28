package com.all.lin.sign.stu._15command._03command;

import java.io.Serializable;

/**
 * 请求接收者。因为Command依赖Operator，它也将随Command对象一起序列化， 所以Operator也实现Serializable接口
 * 
 * @author w1992wishes
 * @created @2017年11月3日-下午5:25:35
 *
 */
class Operator implements Serializable {
    private static final long serialVersionUID = 4962794574238371441L;

    public void insert(String args) {
        System.out.println("insert operation: " + args);
    }

    public void modify(String args) {
        System.out.println("update operation: " + args);
    }

    public void delete(String args) {
        System.out.println("delete peration: " + args);
    }
}