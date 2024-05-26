package com.hello;
import com.product.Popcorn;
import com.product.PopcornList;

import java.util.Scanner;
import java.util.Map;

public class SelectPopCorn extends Welcome {
    private int[] input;
    private Map<String, String>[] returns;
    private Scanner scanner;
    // PopcornList에서 팝콘 객체 배열 가져오기
    private Popcorn[] popcorns = PopcornList.createPopcornList();

    public SelectPopCorn(Scanner scanner) {
        this.scanner = scanner;
    }

    @SuppressWarnings("unchecked")
    public Map<String, String>[] run(int num, int price, int popcornNum) throws InterruptedException {

        // 팝콘 메뉴 출력
        printlnWithDelay(BLUE + "///////////////////////////////////////" + RESET);
        printlnWithDelay(BLUE + "                                     " + RESET);
        printlnWithDelay(BLUE + "         " + RED + "🍿 팝콘 주문 페이지 입니다" + BLUE + "     " + RESET);
        printlnWithDelay(BLUE + "         " + WHITE + "원하는 메뉴를 입력해주세요" + BLUE + "         " + RESET);
        printlnWithDelay(BLUE + "                                     " + RESET);
        printlnWithDelay(BLUE + "///////////////////////////////////////" + RESET);
        printlnWithDelay("");

        boolean pass = false;
        do {
            // 팝콘 메뉴 출력
            int displayIndex = 1;
            for (int i = 0; i < popcorns.length; i++) {
                if (num != 1) {
                    printlnWithDelay((i + 1) + ": " + popcorns[i].printMenu());
                } else if (num == 1) {
                    String menu = popcorns[i].printSetMenu(price);
                    if (menu == null) {
                        pass = true;
                        continue; // 차액이 음수인 경우 현재 반복 건너뛰기
                    }
                    printlnWithDelay(displayIndex + ": " + menu);
                    displayIndex++;
                }
            }
            printlnWithDelay("0: 종료");
            printlnWithDelay("");

            if (popcornNum == 1) {
                System.out.print("맛을 선택해주세요: ");
                // 사용자로부터 한 가지 팝콘을 선택하도록 숫자 입력 받기
                while (!scanner.hasNextInt()) {
                    System.out.println("유효한 숫자를 입력해주세요.");
                    scanner.next(); // 잘못된 입력을 버림
                }
                input = new int[1];
                input[0] = scanner.nextInt();

                // 입력한 번호에 해당하는 팝콘 정보 출력 (예외 처리는 하지 않음)
                if (input[0] > 0 && input[0] <= popcorns.length) {
                    if (pass) {
                        input[0]++;
                    }
                    printlnWithDelay("");
                    printlnWithDelay("선택하신 팝콘: " + popcorns[input[0] - 1].printInfo());
                    returns = new Map[1];
                    returns[0] = popcorns[input[0] - 1].printSelect();
                    break;
                } else if (input[0] == 0) {
                    printlnWithDelay("프로그램을 종료합니다.");
                    break;
                } else {
                    printlnWithDelay("잘못된 입력입니다. 다시 입력해주세요.");
                }
            } else { // popcornNum이 2이면, 공백을 기준으로 두 숫자를 입력받고, 주문한 두 팝콘을 출력한다.
                System.out.print("팝콘을 두 개 선택해주세요: ");
                // 사용자로부터 두 가지 팝콘을 선택하도록 숫자 입력 받기
                while (!scanner.hasNextInt()) {
                    System.out.println("유효한 숫자를 입력해주세요.");
                    scanner.next(); // 잘못된 입력을 버림
                }
                input = new int[2];
                input[0] = scanner.nextInt();

                while (!scanner.hasNextInt()) {
                    System.out.println("유효한 숫자를 입력해주세요.");
                    scanner.next(); // 잘못된 입력을 버림
                }
                input[1] = scanner.nextInt();

                if (input[0] > 0 && input[0] <= popcorns.length && input[1] > 0 && input[1] <= popcorns.length) {
                    printlnWithDelay("");
                    if (pass) {
                        input[0]++;
                        input[1]++;
                    }
                    printlnWithDelay("선택하신 팝콘: " + popcorns[input[0] - 1].printInfo() + " , " + popcorns[input[1] - 1].printInfo());
                    returns = new Map[2];
                    returns[0] = popcorns[input[0] - 1].printSelect();
                    returns[1] = popcorns[input[1] - 1].printSelect();
                    break;
                } else if (input[0] == 0 || input[1] == 0) {
                    printlnWithDelay("프로그램을 종료합니다.");
                    break;
                } else {
                    printlnWithDelay("잘못된 입력입니다. 다시 입력해주세요.");
                }
            }
        } while (input[0] != 0); // 입력 받은 값이 0이 아닐 때까지 반복

        return returns;
    }
}
