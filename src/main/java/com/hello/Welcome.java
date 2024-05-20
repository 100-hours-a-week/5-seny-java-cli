package com.hello;

import java.util.Scanner;

public class Welcome {
    private int input; // 사용자 입력을 저장할 변수 선언
    private String[] welcomeMenu = {"콤보 주문", "팝콘 주문", "음료 주문", "스낵 주문"}; // 배열저장은 {} 안에

    // ANSI escape codes for colors
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public int run() {
        Scanner scanner = new Scanner(System.in);

        // 화려한 시작 화면
        System.out.println(BLUE + "///////////////////////////////////////" + RESET);
        System.out.println(BLUE + "*                                     *" + RESET);
        System.out.println(BLUE + "*      " + RED + "어서오세요 CGV 매점 키오스크입니다" + BLUE + "     *" + RESET);
        System.out.println(BLUE + "*         " + YELLOW + "원하는 메뉴를 입력해주세요" + BLUE + "         *" + RESET);
        System.out.println(BLUE + "*                                     *" + RESET);
        System.out.println(BLUE + "///////////////////////////////////////" + RESET);

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
