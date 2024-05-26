package com.hello;

import com.order.Order;
import com.product.*;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in); // 사용자 입력을 위한 Scanner 객체 생성
        try {
            Welcome welcome = new Welcome();
            int menuSelection = welcome.run();
            System.out.println(menuSelection);

            SelectCombo selectCombo = new SelectCombo(scanner);
            SelectPopCorn selectPopCorn = new SelectPopCorn(scanner);
            SelectHalfPopCorn selectHalfPopCorn = new SelectHalfPopCorn(scanner);
            SelectBeverage selectBeverage = new SelectBeverage(scanner);
            SelectSnack selectSnack = new SelectSnack(scanner);

            if (menuSelection == 1) {
                handleSetMenu(selectCombo, selectPopCorn, selectHalfPopCorn, selectBeverage, scanner);
            } else if (menuSelection == 2) {
                handlePopcornOrder(selectPopCorn, selectHalfPopCorn);
            } else if (menuSelection == 3) {
                handleBeverageOrder(selectBeverage);
            } else if (menuSelection == 4) {
                handleSnackOrder(selectSnack);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close(); // Scanner 객체를 닫아 리소스를 해제합니다.
        }
    }

    private static void handleSetMenu(SelectCombo selectCombo, SelectPopCorn selectPopCorn, SelectHalfPopCorn selectHalfPopCorn, SelectBeverage selectBeverage, Scanner scanner) throws InterruptedException {
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
                    String halfPopcornDetails = "선택하신 반반 팝콘 맛: " + popcorns[halfSelection[0] - 1].getName() + " + " + popcorns[halfSelection[1] - 1].getName();
                    System.out.println(halfPopcornDetails);
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

    private static void handlePopcornOrder(SelectPopCorn selectPopCorn, SelectHalfPopCorn selectHalfPopCorn) throws InterruptedException {
        Map<String, String>[] popcornSelection = selectPopCorn.run(2, 0, 1);
        Popcorn[] popcorns = PopcornList.createPopcornList();
        if (popcornSelection[0] != null && popcornSelection[0].get("name").equals("반반팝콘")) {
            int[] halfSelection = selectHalfPopCorn.selectHalfPopcorn();
            Order order = new Order(popcorns[8], halfSelection[0], halfSelection[1]);
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
            Order order = new Order(popcorns[selectedIndex]);
            System.out.println(order.totalOrder());
            System.out.println(order.totalPrice());
        }
    }



    private static void handleBeverageOrder(SelectBeverage selectBeverage) throws InterruptedException {
        Map<String, String>[] beverageSelection = selectBeverage.run(3, 0, 1);
        Beverage[] beverages = BeverageList.createBeverageList();

        // Fix: Use the correct index to fetch the selected beverage
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


    private static void handleSnackOrder(SelectSnack selectSnack) throws InterruptedException {
        int snackSelection = selectSnack.run(4);
        Snack[] snacks = SnackList.createSnackList();
        Order order = new Order(snacks[snackSelection - 1]);
        System.out.println(order.totalOrder());
        System.out.println(order.totalPrice());
    }
}
