package com.hello;

import java.util.Scanner;

public class SelectHalfPopCorn extends Welcome {
    private String[] popcorns = {"고소", "달콤", "더블치즈", "바질어니언"};
    private Scanner scanner;

    public SelectHalfPopCorn(Scanner scanner) {
        this.scanner = scanner;
    }

    public int[] selectHalfPopcorn() throws InterruptedException {
        printlnWithDelay(BLUE + "///////////////////////////////////////" + RESET);
        printlnWithDelay(BLUE + "                                     " + RESET);
        printlnWithDelay(BLUE + "       " + RED + "🍿 반반 팝콘 주문 페이지 입니다" + BLUE + "     " + RESET);
        printlnWithDelay(BLUE + "       " + WHITE + "   두 가지 맛을 선택해주세요" + BLUE + "         " + RESET);
        printlnWithDelay(BLUE + "                                     " + RESET);
        printlnWithDelay(BLUE + "///////////////////////////////////////" + RESET);
        printlnWithDelay("");

        StringBuilder menu = new StringBuilder();
        for (int i = 0; i < popcorns.length; i++) {
            menu.append((i + 1)).append(". ").append(popcorns[i]);
            if (i < popcorns.length - 1) {
                menu.append("  ");
            }
        }
        printlnWithDelay(menu.toString());
        printlnWithDelay("");

        int[] selections = new int[2];

        do {
            System.out.print("두 가지 맛을 선택해주세요 (예: 1 2) ");
            String input = scanner.nextLine();
            String[] inputs = input.split(" "); // 입력값 공백 기준으로 배열에 저장

            if (inputs.length != 2) {
                printlnWithDelay("잘못된 입력입니다. 두 개의 숫자를 띄어쓰기로 구분하여 입력해주세요.");
                continue;
            }

            try {
                selections[0] = Integer.parseInt(inputs[0]);
                selections[1] = Integer.parseInt(inputs[1]);

                if (selections[0] < 1 || selections[0] > popcorns.length || selections[1] < 1 || selections[1] > popcorns.length) {
                    printlnWithDelay("유효한 숫자를 입력해주세요.");
                    continue;
                }

                if (selections[0] == selections[1]) {
                    printlnWithDelay("같은 숫자를 두 번 선택할 수 없습니다. 다른 숫자를 입력해주세요.");
                    continue;
                }

                printlnWithDelay("선택하신 반반팝콘 맛: " + popcorns[selections[0] - 1] + " + " + popcorns[selections[1] - 1]);
                break;
            } catch (NumberFormatException e) {
                printlnWithDelay("잘못된 입력입니다. 숫자를 입력해주세요.");
            }
        } while (true);

        return selections;
    }
}
