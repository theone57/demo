package com.all.lin.sign.stu._4factory_interface.factorymethod.pizzastore.order;

import com.all.lin.sign.stu._4factory_interface.factorymethod.pizzastore.pizza.BJCheesePizza;
import com.all.lin.sign.stu._4factory_interface.factorymethod.pizzastore.pizza.BJPepperPizza;
import com.all.lin.sign.stu._4factory_interface.factorymethod.pizzastore.pizza.Pizza;


public class BJOrderPizza extends OrderPizza {


	@Override
    Pizza createPizza(String orderType) {

		Pizza pizza = null;
		if(orderType.equals("cheese")) {
			pizza = new BJCheesePizza();
		} else if (orderType.equals("pepper")) {
			pizza = new BJPepperPizza();
		}
		// TODO Auto-generated method stub
		return pizza;
	}

}
