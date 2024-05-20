package com.hello;

import java.util.Scanner;

public class Welcome {
    private int input; // 사용자 입력을 저장할 변수 선언
    private String[] welcomeMenu = {"콤보 주문", "팝콘 주문", "음료 주문", "스낵 주문"}; // 배열저장은 {} 안에

    public int run() {
        Scanner scanner = new Scanner(System.in);

        do {
            // 메뉴 출력
            for (int i = 0; i < welcomeMenu.length; i++) {
                System.out.println((i + 1) + ". " + welcomeMenu[i]);
            }
            System.out.println("0: 종료");
            System.out.print("메뉴를 선택해주세요: ");

            // 사용자로부터 다음 메뉴를 선택하도록 숫자 입력 받기
            while (!scanner.hasNextInt()) {
                System.out.println("유효한 숫자를 입력해주세요.");
                scanner.next(); // 잘못된 입력을 버림
            }
            input = scanner.nextInt();

            if (input > 0 && input <= welcomeMenu.length) {
                System.out.println("선택하신 메뉴: " + welcomeMenu[input - 1]);
                break;
            } else if (input == 0) {
                System.out.println("프로그램을 종료합니다.");
                break;
            } else {
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            }

        } while (input != 0);

        return input;
    }
}
