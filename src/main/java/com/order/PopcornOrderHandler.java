package com.order;

import com.order.Order;
import com.product.Popcorn;
import com.product.PopcornList;
import com.hello.SelectHalfPopCorn;
import com.hello.SelectPopCorn;

import java.util.Map;

public class PopcornOrderHandler implements HandleMenu {
    private SelectPopCorn selectPopCorn;
    private SelectHalfPopCorn selectHalfPopCorn;
    private Order order;

    public PopcornOrderHandler(SelectPopCorn selectPopCorn, SelectHalfPopCorn selectHalfPopCorn) {
        this.selectPopCorn = selectPopCorn;
        this.selectHalfPopCorn = selectHalfPopCorn;
    }

    @Override
    // 인터페이스를 구현하는 클래스들은 handle 메서드를 구현해야 합니다. 각 핸들러 클래스는 특정 메뉴를 처리하는 역할을 합니다.
    public void handle() throws InterruptedException {
        Map<String, String>[] popcornSelection = selectPopCorn.run(2, 0, 1);
        Popcorn[] popcorns = PopcornList.createPopcornList();
        if (popcornSelection[0] != null && popcornSelection[0].get("name").equals("반반팝콘")) {
            int[] halfSelection = selectHalfPopCorn.selectHalfPopcorn();
            order = new Order(popcorns[8], halfSelection[0], halfSelection[1]); // order 객체 초기화
            System.out.println(order.totalOrder());
            System.out.println(order.totalPrice());
        } else {
            assert popcornSelection[0] != null;
            String selectedPopcornName = popcornSelection[0].get("name");
            String selectedPopcornSize = popcornSelection[0].get("size");

            int selectedIndex = -1;
            for (int i = 0; i < popcorns.length; i++) {
                if (popcorns[i].getName().equals(selectedPopcornName) && popcorns[i].getSize().equals(selectedPopcornSize)) {
                    selectedIndex = i;
                    break;
                }
            }
            order = new Order(popcorns[selectedIndex]);
            System.out.println(order.totalOrder());
            System.out.println(order.totalPrice());
        }
    }

    @Override
    public Order getOrder() {
        return order;
    }
}

