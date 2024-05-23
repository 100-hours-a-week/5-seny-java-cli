package com.hello;

import com.product.Combo;
import com.product.ComboList;

import java.util.Scanner;

public class SelectCombo extends Welcome {
    private int input;
    private Combo[] combos = ComboList.createComboList();

    public int run(int num) throws InterruptedException {
        Scanner scanner = new Scanner(System.in); // 사용자 입력을 위한 Scanner 객체 생성

        // 팝콘 메뉴 출력
        printlnWithDelay(BLUE + "///////////////////////////////////////" + RESET);
        printlnWithDelay(BLUE + "                                     " + RESET);
        printlnWithDelay(BLUE + "       " + RED + "🍿 콤보 메뉴 주문 페이지 입니다" + BLUE + "     " + RESET);
        printlnWithDelay(BLUE + "         " + WHITE + "원하는 메뉴를 입력해주세요" + BLUE + "         " + RESET);
        printlnWithDelay(BLUE + "                                     " + RESET);
        printlnWithDelay(BLUE + "///////////////////////////////////////" + RESET);
        printlnWithDelay("");

        do {
            // 세트 메뉴 출력
            for (int i = 0; i < combos.length; i++) {
                printlnWithDelay((i+1)+ ": " + combos[i].printSetMenu());
            }
            printlnWithDelay("0: 종료");
            printlnWithDelay("");
            System.out.print("세트 메뉴를 선택해주세요: ");

            // 사용자로부터 다음 팝콘을 선택하도록 숫자 입력 받기
            while (!scanner.hasNextInt()) {
                System.out.println("유효한 숫자를 입력해주세요.");
                scanner.next(); // 잘못된 입력을 버림
            }
            input = scanner.nextInt();

            // 입력한 번호에 해당하는 팝콘 정보 출력 (예외 처리는 하지 않음)
            if (input > 0 && input <= combos.length) {
                printlnWithDelay("");
                printlnWithDelay("선택하신 콤보: " + combos[input - 1].getName());
                break;
            } else if (input == 0) {
                printlnWithDelay("프로그램을 종료합니다.");
                break;
            } else {
                printlnWithDelay("잘못된 입력입니다. 다시 입력해주세요.");
            }
        } while (input != 0); // 입력 받은 값이 0이 아닐 때까지 반복

        return input;

        }


    }



