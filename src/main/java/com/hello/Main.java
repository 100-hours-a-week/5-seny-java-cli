package com.hello;

import com.order.Order;
import com.product.*;

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
//            System.out.println(defPriceP +","+ defPriceB);
            int popcornSelection = selectPopCorn.run(1, defPriceP);
            int BeverageSelection = selectBeverage.run(1, defPriceB);
            int totalPrice = Integer.parseInt(setInfo[1]); // 세트 가격
            int popcornNum = Integer.parseInt(setInfo[2]); // 팝콘 개수

        }

        // 사용자가 2번을 선택한 경우 팝콘 메뉴로 이동
        else if (menuSelection == 2) {

            int popcornSelction = selectPopCorn.run(2, 0); // 세트메뉴인 경우에만 price에 의미 있음.
            // 주문처리
            Popcorn[] popcorns = PopcornList.createPopcornList();
            if (popcornSelction == 9) {
                System.out.println("반반 팝콘을 선택하셨습니다.");
                int[] halfSelection = selectHalfPopCorn.selectHalfPopcorn();
                Order order = new Order(popcorns[popcornSelction-1], halfSelection[0], halfSelection[1]);
                System.out.println(order.totalOrder());
                System.out.println(order.totalPrice());
            }
            else {
                Order order = new Order(popcorns[popcornSelction-1]);
                System.out.println(order.totalOrder());
                System.out.println(order.totalPrice());
            }

        }
        else if (menuSelection == 3) {
            int beverageSelction = selectBeverage.run(3,0);
            // 음료 주문처리
            Beverage[] beverages = BeverageList.createBeverageList();
            Order order = new Order(beverages[beverageSelction-1]);
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
