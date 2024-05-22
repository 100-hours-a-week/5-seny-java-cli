package com.hello;
import com.product.Popcorn;
import com.product.PopcornList;

import java.util.Scanner;

public class SelectPopCorn extends Welcome {
    private int input;
    // PopcornList에서 팝콘 객체 배열 가져오기
    private Popcorn[] popcorns = PopcornList.createPopcornList();

    public int run(int num) throws InterruptedException {
        Scanner scanner = new Scanner(System.in); // 사용자 입력을 위한 Scanner 객체 생성

        // 팝콘 메뉴 출력
        printlnWithDelay(BLUE + "///////////////////////////////////////" + RESET);
        printlnWithDelay(BLUE + "                                     " + RESET);
        printlnWithDelay(BLUE + "         " + RED + "🍿 팝콘 주문 페이지 입니다" + BLUE + "     " + RESET);
        printlnWithDelay(BLUE + "         " + WHITE + "원하는 메뉴를 입력해주세요" + BLUE + "         " + RESET);
        printlnWithDelay(BLUE + "                                     " + RESET);
        printlnWithDelay(BLUE + "///////////////////////////////////////" + RESET);
        printlnWithDelay("");

        do {
            // 팝콘 메뉴 출력
            for (int i = 0; i < popcorns.length; i++) {
                if (num != 1) {
                    printlnWithDelay((i + 1) + ": " + popcorns[i].printMenu());
                }
                else if (num == 1){
                    printlnWithDelay((i + 1) + ": " + popcorns[i].printSetMenu());
                }
            }
            printlnWithDelay("0: 종료");
            printlnWithDelay("");
            System.out.print("맛을 선택해주세요: ");

            // 사용자로부터 다음 팝콘을 선택하도록 숫자 입력 받기
            while (!scanner.hasNextInt()) {
                System.out.println("유효한 숫자를 입력해주세요.");
                scanner.next(); // 잘못된 입력을 버림
            }
            input = scanner.nextInt();

            // 입력한 번호에 해당하는 팝콘 정보 출력 (예외 처리는 하지 않음)
            if (input > 0 && input <= popcorns.length) {
                printlnWithDelay("");
                printlnWithDelay("선택하신 팝콘: " + popcorns[input - 1].printInfo());
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
