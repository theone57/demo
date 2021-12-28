package com.all.lin.sign.stu._7adapter.objectadapter;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(" === 对象适配器模式 ====");
		Phone phone = new Phone();
		VoltageAdapter iVoltage5V = new VoltageAdapter(new Voltage220V());
		phone.charging(iVoltage5V);
	}

}
