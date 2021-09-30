package com.lin.demo.sign.lp.factory;

//具体产品类A
class ProductA extends Product {

    @Override
    public void show() {
        System.out.println("生产出了产品A");
    }
}

//具体产品类B
class ProductB extends Product {

    @Override
    public void show() {
        System.out.println("生产出了产品B");
    }

}

//具体产品类C
class ProductC extends Product {

    @Override
    public void show() {
        System.out.println("生产出了产品C");
    }

}