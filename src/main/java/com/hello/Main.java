package com.hello;

import com.order.Order;
import com.product.*;

import java.util.Map;
import java.util.Scanner;
///// 여기까지 돌아가면 됩ㄴ디아.ㅇ
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in); // 사용자 입력을 위한 Scanner 객체 생성
        // Welcome 클래스 사용하여 메뉴 선택
        Welcome welcome = new Welcome();
        // 첫 반복문
        int menuSelection = welcome.run();
        System.out.println(menuSelection);

        SelectCombo selectCombo = new SelectCombo();
        SelectPopCorn selectPopCorn = new SelectPopCorn();
        SelectHalfPopCorn selectHalfPopCorn = new SelectHalfPopCorn();
        SelectBeverage selectBeverage = new SelectBeverage();
        SelectSnack selectSnack = new SelectSnack();

        // 사용자가 1번을 선택한 경우 세트 메뉴로 이동 ( 팝콘 선택 - 음료선택 - 스낵 추가 선택 순서 )
        if (menuSelection == 1) {
            int comboSelection = selectCombo.run(1);
            // 주문처리
            Combo[] combos = ComboList.createComboList();
            String[] setInfo = combos[comboSelection-1].getInfo();
            int defPriceP = Integer.parseInt(setInfo[6]); // 팝콘 기준가
            int defPriceB = Integer.parseInt(setInfo[7]); // 음료 기준가

            int popcornNum = Integer.parseInt(setInfo[2]); // 팝콘 입력받아야하는 개수
            int beverageNum = Integer.parseInt(setInfo[5]); // 음료 입력받아야하는 개수
            Map<String, String>[] popcornSelection = selectPopCorn.run(1, defPriceP, popcornNum);
            Map<String, String>[] beverageSelection = selectBeverage.run(1, defPriceB, beverageNum);
            int totalPrice = Integer.parseInt(setInfo[1]); // 세트 가격
            // 팝콘 가격 추가
            for (Map<String, String> popcorn : popcornSelection) {
                if (popcorn != null) {
                    totalPrice += Integer.parseInt(popcorn.get("price"));
                }
            }

            // 음료 가격 추가
            for (Map<String, String> beverage : beverageSelection) {
                if (beverage != null) {
                    totalPrice += Integer.parseInt(beverage.get("price"));
                }
            }
            System.out.println("총 가격: " + totalPrice + "원");

        }

        // 사용자가 2번을 선택한 경우 팝콘 메뉴로 이동
        else if (menuSelection == 2) {
            Map<String, String>[] popcornSelection = selectPopCorn.run(2, 0, 1);
            // 주문처리
            Popcorn[] popcorns = PopcornList.createPopcornList();
            if (popcornSelection[0] != null && popcornSelection[0].get("name").equals("반반팝콘")) {
                System.out.println("반반 팝콘을 선택하셨습니다.");
                int[] halfSelection = selectHalfPopCorn.selectHalfPopcorn();
                Order order = new Order(popcorns[8], halfSelection[0], halfSelection[1]);
                System.out.println(order.totalOrder());
                System.out.println(order.totalPrice());
            } else {
                assert popcornSelection[0] != null;
                int selectedIndex = Integer.parseInt(popcornSelection[0].get("name").substring(0, 1)) - 1;
                Order order = new Order(popcorns[selectedIndex]);
                System.out.println(order.totalOrder());
                System.out.println(order.totalPrice());
            }
        }
        // 사용자가 3번을 선택한 경우 음료 메뉴로 이동
        else if (menuSelection == 3) {
            Map<String, String>[] beverageSelection = selectBeverage.run(3, 0, 1);
            // 음료 주문처리
            Beverage[] beverages = BeverageList.createBeverageList();
            int selectedIndex = Integer.parseInt(beverageSelection[0].get("name").substring(0, 1)) - 1;
            Order order = new Order(beverages[selectedIndex]);
            System.out.println(order.totalOrder());
            System.out.println(order.totalPrice());
        }
        else if (menuSelection == 4){
            // 스낵 선택
            int snackSelection = selectSnack.run(4);
            Snack[] snacks = SnackList.createSnackList();
            Order order = new Order(snacks[snackSelection-1]);
            System.out.println(order.totalOrder());
            System.out.println(order.totalPrice());
        }
        scanner.close(); // Scanner 객체를 닫아 리소스를 해제합니다.
    }
}
