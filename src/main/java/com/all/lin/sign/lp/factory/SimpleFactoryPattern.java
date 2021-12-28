package com.all.lin.sign.lp.factory;

//工厂产品生产流程
public class SimpleFactoryPattern {

    public static void main(String[] args){

        //客户要产品A
        try {
        //调用工厂类的静态方法 & 传入不同参数从而创建产品实例
            Factory.manufacture("A").show();
        }catch (NullPointerException e){
            System.out.println("没有这一类产品");
        }

        //客户要产品B
        try {
            Factory.manufacture("B").show();
        }catch (NullPointerException e){
            System.out.println("没有这一类产品");
        }

        //客户要产品C
        try {
            Factory.manufacture("C").show();
        }catch (NullPointerException e){
            System.out.println("没有这一类产品");
        }

        //客户要产品D
        try {
            Factory.manufacture("D").show();
        }catch (NullPointerException e){
            System.out.println("没有这一类产品");
        }
    }
}
