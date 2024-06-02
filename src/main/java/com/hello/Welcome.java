package com.hello;

import java.util.Scanner;

public class Welcome {
    private int input; // 사용자 입력을 저장할 변수 선언
    private String[] welcomeMenu = {"콤보 주문 👍", "팝콘 주문 🍿", "음료 주문 🥤", "스낵 주문 🍕"}; // 배열저장은 {} 안에

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

    public static final int DELAY = 000; // 밀리초 단위로 지연시간 설정 (0.3초)

    public int run() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        // 텍스트 한 줄씩 출력
        try {
            printlnWithDelay("");
            printlnWithDelay("");
            printlnWithDelay("             Welcome to");
            printlnWithDelay("");
            printlnWithDelay("");
            printlnWithDelay("     //////    //////   //       //    " + RESET);
            printlnWithDelay("    //    //  //    //  //       //     " + RESET);
            printlnWithDelay("    //        //         //     //" + RESET);
            printlnWithDelay("    //        //   ///    //   //    " + RESET);
            printlnWithDelay("    //        //    //     // //        " + RESET);
            printlnWithDelay("    //    //  //    //      ///       " + RESET);
            printlnWithDelay("     //////    //////        /        " + RESET);
            printlnWithDelay("");
            printlnWithDelay("");
            printlnWithDelay(BLUE + "///////////////////////////////////////" + RESET);
            printlnWithDelay(BLUE + "                                     " + RESET);
            printlnWithDelay(BLUE + "   " + WHITE + "🤗 어서오세요" + RED +" CGV 매점"+WHITE+" 키오스크입니다 🤗" + BLUE + "     " + RESET);
            printlnWithDelay(BLUE + "         " + WHITE + "원하는 메뉴를 입력해주세요" + BLUE + "         " + RESET);
            printlnWithDelay(BLUE + "                                     " + RESET);
            printlnWithDelay(BLUE + "///////////////////////////////////////" + RESET);
            printlnWithDelay("");
        } catch (InterruptedException e) {
            System.out.println("출력 중 인터럽트 발생: " + e.getMessage());
        }

        do {
            // 메뉴 출력
            for (int i = 0; i < welcomeMenu.length; i++) {
                System.out.println((i + 1) + ". " + welcomeMenu[i]);
            }
            System.out.println("0: 종료");
            printlnWithDelay("");
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

    // 한 줄씩 출력하는 메서드
    public void printlnWithDelay(String text) throws InterruptedException {
        System.out.println(text);
        Thread.sleep(DELAY);
    }

    public static void main(String[] args) throws InterruptedException {
        Welcome welcome = new Welcome();
        welcome.run();
    }
}
