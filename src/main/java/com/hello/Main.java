package com.hello;

import com.order.Order;
import com.product.Popcorn;
import com.product.PopcornList;
import com.product.Product;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // 사용자 입력을 위한 Scanner 객체 생성
        // Welcome 클래스 사용하여 메뉴 선택
        Welcome welcome = new Welcome();
        // 첫 반복문
        int menuSelection = welcome.run();
        System.out.println(menuSelection);

        // 사용자가 2번을 선택한 경우 팝콘 메뉴로 이동
        if (menuSelection == 2) {
            Popcorn[] popcorns = PopcornList.createPopcornList();
            SelectPopCorn selectPopCorn = new SelectPopCorn();
            int popcornSelction = selectPopCorn.run();
            scanner.close(); // Scanner 객체를 닫아 리소스를 해제합니다.

            Order order = new Order(popcorns[popcornSelction-1]);
            System.out.println(order.totalPrice());
        }
    }
}
