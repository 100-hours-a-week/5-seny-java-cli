package com.hello;

import com.order.Order;
import com.product.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in); // 사용자 입력을 위한 Scanner 객체 생성
        // Welcome 클래스 사용하여 메뉴 선택
        Welcome welcome = new Welcome();
        // 첫 반복문
        int menuSelection = welcome.run();
        System.out.println(menuSelection);

        // 사용자가 1번을 선택한 경우 세트 메뉴로 이동 ( 팝콘 선택 - 음료선택 - 스낵 추가 선택 순서 )
        if (menuSelection == 1) {

        }

        // 사용자가 2번을 선택한 경우 팝콘 메뉴로 이동
        else if (menuSelection == 2) {
            SelectPopCorn selectPopCorn = new SelectPopCorn();
            int popcornSelction = selectPopCorn.run(2);
            // 주문처리
            Popcorn[] popcorns = PopcornList.createPopcornList();
            if (popcornSelction == 9) {
                System.out.println("반반 팝콘을 선택하셨습니다.");
                SelectHalfPopCorn selectHalfPopCorn = new SelectHalfPopCorn();
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
            SelectBeverage selectBeverage = new SelectBeverage();
            int beverageSelction = selectBeverage.run(3);
            // 음료 주문처리
            Beverage[] beverages = BeverageList.createBeverageList();
            Order order = new Order(beverages[beverageSelction-1]);
            System.out.println(order.totalOrder());
            System.out.println(order.totalPrice());
        }
        else if (menuSelection == 4){
            // 스낵 선택
            SelectSnack selectSnack = new SelectSnack();
            int snackSelection = selectSnack.run(4);
            Snack[] snacks = SnackList.createSnackList();
            Order order = new Order(snacks[snackSelection-1]);
            System.out.println(order.totalOrder());
            System.out.println(order.totalPrice());
        }
        scanner.close(); // Scanner 객체를 닫아 리소스를 해제합니다.
    }
}
