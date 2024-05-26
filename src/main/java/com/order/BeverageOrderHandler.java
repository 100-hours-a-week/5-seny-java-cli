package com.order;


import com.product.Beverage;
import com.product.BeverageList;
import com.hello.SelectBeverage;

import java.util.Map;

public class BeverageOrderHandler implements HandleMenu {
    private SelectBeverage selectBeverage;

    public BeverageOrderHandler(SelectBeverage selectBeverage) {
        this.selectBeverage = selectBeverage;
    }

    @Override
    public void handle() throws InterruptedException {
        Map<String, String>[] beverageSelection = selectBeverage.run(3, 0, 1);
        Beverage[] beverages = BeverageList.createBeverageList();

        String selectedBeverageName = beverageSelection[0].get("name");
        String selectedBeverageSize = beverageSelection[0].get("size");

        int selectedIndex = -1;
        for (int i = 0; i < beverages.length; i++) {
            if (beverages[i].getName().equals(selectedBeverageName) && beverages[i].getSize().equals(selectedBeverageSize)) {
                selectedIndex = i;
                break;
            }
        }

        Order order = new Order(beverages[selectedIndex]);
        System.out.println(order.totalOrder());
        System.out.println(order.totalPrice());
    }
}
