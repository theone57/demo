package com.lin.demo.sign.stu._7adapter.interfaceadapter;

public class Client {
	public static void main(String[] args) {
		AbsAdapter absAdapter = new AbsAdapter(){
			@Override
			public void m1() {

			}
		};
//		AbsAdapter absAdapter = new AbsAdapter() {
//			//只需要去覆盖我们 需要使用 接口方法
//			@Override
//			public void m1() {
//				// TODO Auto-generated method stub
//				System.out.println("使用了m1的方法");
//			}
//		};

		absAdapter.m1();

		AbsAdapter absAdapter1 = new AbsAdapter(){
			@Override
			public void m2() {
				System.out.println("absAdapter = " + absAdapter);
			}
		};
		absAdapter1.m1();
	}
}
