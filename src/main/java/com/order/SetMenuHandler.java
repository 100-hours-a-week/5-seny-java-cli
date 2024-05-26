package com.order;

import com.product.Combo;
import com.product.ComboList;
import com.product.Popcorn;
import com.hello.SelectBeverage;
import com.hello.SelectCombo;
import com.hello.SelectHalfPopCorn;
import com.hello.SelectPopCorn;
import com.product.PopcornList;

import java.util.Map;
import java.util.Scanner;

public class SetMenuHandler implements HandleMenu {
    private SelectCombo selectCombo;
    private SelectPopCorn selectPopCorn;
    private SelectHalfPopCorn selectHalfPopCorn;
    private SelectBeverage selectBeverage;
    private Scanner scanner;

    public SetMenuHandler(SelectCombo selectCombo, SelectPopCorn selectPopCorn, SelectHalfPopCorn selectHalfPopCorn, SelectBeverage selectBeverage, Scanner scanner) {
        this.selectCombo = selectCombo;
        this.selectPopCorn = selectPopCorn;
        this.selectHalfPopCorn = selectHalfPopCorn;
        this.selectBeverage = selectBeverage;
        this.scanner = scanner;
    }

    @Override
    public void handle() throws InterruptedException {
        int comboSelection = selectCombo.run(1);
        Combo[] combos = ComboList.createComboList();
        String[] setInfo = combos[comboSelection - 1].getInfo();
        int defPriceP = Integer.parseInt(setInfo[6]); // 팝콘 기준가
        int defPriceB = Integer.parseInt(setInfo[7]); // 음료 기준가

        int popcornNum = Integer.parseInt(setInfo[2]); // 팝콘 입력받아야하는 개수
        int beverageNum = Integer.parseInt(setInfo[5]); // 음료 입력받아야하는 개수
        Map<String, String>[] popcornSelection = selectPopCorn.run(1, defPriceP, popcornNum);
        Map<String, String>[] beverageSelection = selectBeverage.run(1, defPriceB, beverageNum);

        int initialPrice = combos[comboSelection - 1].getPrice();
        Order order = new Order(initialPrice);

        System.out.println("\n주문 내역");
        System.out.println("세트 가격: " + initialPrice + "원");

        for (Map<String, String> popcorn : popcornSelection) {
            if (popcorn != null) {
                if (popcorn.get("name").equals("반반팝콘")) {
                    int[] halfSelection = selectHalfPopCorn.selectHalfPopcorn();
                    Popcorn[] popcorns = PopcornList.createPopcornList();
//                    String halfPopcornDetails = "선택하신 반반 팝콘 맛: " + popcorns[halfSelection[0] - 1].getName() + " + " + popcorns[halfSelection[1] - 1].getName();
//                    System.out.println(halfPopcornDetails);
                    order.addItem("반반팝콘", Integer.parseInt(popcorn.get("price")));
                } else {
                    order.addItem(popcorn.get("name"), Integer.parseInt(popcorn.get("price")));
                }
            }
        }

        for (Map<String, String> beverage : beverageSelection) {
            if (beverage != null) {
                order.addItem(beverage.get("name"), Integer.parseInt(beverage.get("price")));
            }
        }

        System.out.println(order.getOrderDetails());
        System.out.println("총 가격: " + order.getTotalPrice() + "원");
    }
}
