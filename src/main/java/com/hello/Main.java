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

        // 사용자가 2번을 선택한 경우 팝콘 메뉴로 이동
        if (menuSelection == 2) {
            SelectPopCorn selectPopCorn = new SelectPopCorn();
            int popcornSelction = selectPopCorn.run();

            // 스낵 선택
            SelectSnack selectSnack = new SelectSnack();
            int snackSelection = selectSnack.run();

            // 주문처리
            Popcorn[] popcorns = PopcornList.createPopcornList();
            Snack[] snacks = SnackList.createSnackList();
            scanner.close(); // Scanner 객체를 닫아 리소스를 해제합니다.
            Order order = new Order(popcorns[popcornSelction-1], snacks[snackSelection-1]);
            System.out.println(order.totalOrder());
            System.out.println(order.totalPrice());
        }
    }
}
