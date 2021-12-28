package com.all.lin.sign.stu._4factory_interface.factorymethod.pizzastore.order;

import com.all.lin.sign.stu._4factory_interface.factorymethod.pizzastore.pizza.LDCheesePizza;
import com.all.lin.sign.stu._4factory_interface.factorymethod.pizzastore.pizza.LDPepperPizza;
import com.all.lin.sign.stu._4factory_interface.factorymethod.pizzastore.pizza.Pizza;

public class LDOrderPizza extends OrderPizza {

	
	@Override
    Pizza createPizza(String orderType) {
	
		Pizza pizza = null;
		if(orderType.equals("cheese")) {
			pizza = new LDCheesePizza();
		} else if (orderType.equals("pepper")) {
			pizza = new LDPepperPizza();
		}
		// TODO Auto-generated method stub
		return pizza;
	}

}
