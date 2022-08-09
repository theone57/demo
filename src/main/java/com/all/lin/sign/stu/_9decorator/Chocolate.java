package com.all.lin.sign.stu._9decorator;

//具体的Decorator， 这里就是调味品
public class Chocolate extends Decorator {

	public Chocolate(Drink obj) {
		super(obj);
		setDes(" 巧克力 ");
		setPrice(3.0f); // 调味品 的价格
	}

	public static void main(String[] args) {
		System.out.println("春年");
	}
}
