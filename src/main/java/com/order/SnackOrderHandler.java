package com.order;

import com.hello.SelectSnack;
import com.product.Snack;
import com.product.SnackList;

public class SnackOrderHandler implements HandleMenu {
    private SelectSnack selectSnack;

    public SnackOrderHandler(SelectSnack selectSnack) {
        this.selectSnack = selectSnack;
    }

    @Override
    public void handle() throws InterruptedException {
        int snackSelection = selectSnack.run(4);
        Snack[] snacks = SnackList.createSnackList();
        Order order = new Order(snacks[snackSelection - 1]);
        System.out.println(order.totalOrder());
        System.out.println(order.totalPrice());
    }
}

