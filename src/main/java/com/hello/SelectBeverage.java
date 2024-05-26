package com.hello;

import com.product.Beverage;
import com.product.BeverageList;

import java.util.Map;
import java.util.Scanner;

public class SelectBeverage extends Welcome {
    private int input;
    private Map<String, String>[] returns; // 선택한 음료수의 정보를 담은 배열
    private Scanner scanner; // Scanner 객체를 필드로 선언
    // BeverageList에서 음료 객체 배열 가져오기
    private Beverage[] beverages = BeverageList.createBeverageList();

    public SelectBeverage(Scanner scanner) {
        this.scanner = scanner;
    }

    @SuppressWarnings("unchecked")
    public Map<String, String>[] run(int num, int price, int selectNum) throws InterruptedException {
        returns = (Map<String, String>[]) new Map[selectNum]; // 선택한 음료 정보를 저장할 배열

        // 음료 메뉴 출력
        printlnWithDelay(BLUE + "///////////////////////////////////////" + RESET);
        printlnWithDelay(BLUE + "                                     " + RESET);
        printlnWithDelay(BLUE + "         " + RED + "🥤 음료 주문 페이지 입니다" + BLUE + "     " + RESET);
        printlnWithDelay(BLUE + "         " + WHITE + "원하는 메뉴를 입력해주세요" + BLUE + "         " + RESET);
        printlnWithDelay(BLUE + "                                     " + RESET);
        printlnWithDelay(BLUE + "///////////////////////////////////////" + RESET);
        printlnWithDelay("");
        boolean pass = false;
        do {
            // 음료 메뉴 출력
            int displayIndex = 1;
            for (int i = 0; i < beverages.length; i++) {
                if (num != 1) {
                    printlnWithDelay((i + 1) + ": " + beverages[i].printMenu());
                } else if (num == 1) {
                    String menu = beverages[i].printSetMenu(price);
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
            if (selectNum == 1) {
                System.out.print("음료를 선택해주세요: ");
                // 사용자로부터 한 가지 음료를 선택하도록 숫자 입력 받기
                while (!scanner.hasNextInt()) {
                    System.out.println("유효한 숫자를 입력해주세요.");
                    scanner.next(); // 잘못된 입력을 버림
                }
                input = scanner.nextInt();

                // 입력한 번호에 해당하는 음료 정보 출력 (예외 처리는 하지 않음)
                if (input > 0 && input <= beverages.length) {
                    printlnWithDelay("");
                    printlnWithDelay("선택하신 음료: " + beverages[input - 1].printInfo());
                    returns[0] = beverages[input - 1].printSelect(); // 선택한 음료 정보를 저장
                    break;
                } else if (input == 0) {
                    printlnWithDelay("프로그램을 종료합니다.");
                    break;
                } else {
                    printlnWithDelay("잘못된 입력입니다. 다시 입력해주세요.");
                }
            } else if (selectNum == 2) {
                System.out.print("음료를 두 가지 선택해주세요.(예: 1 2) : ");
                // 사용자로부터 두 가지 음료를 선택하도록 숫자 입력 받기
                scanner.nextLine(); // 버퍼 비우기
                String inputLine;
                int[] selections = new int[2];
                while (true) {
                    inputLine = scanner.nextLine();
                    String[] inputs = inputLine.split(" ");
                    if (inputs.length == 2) {
                        try {
                            selections[0] = Integer.parseInt(inputs[0]);
                            selections[1] = Integer.parseInt(inputs[1]);

                            if ((selections[0] > 0 && selections[0] <= beverages.length) && (selections[1] > 0 && selections[1] <= beverages.length)) {
                                printlnWithDelay("");
                                printlnWithDelay("선택하신 음료: " + beverages[selections[0] - 1].printInfo() + " + " + beverages[selections[1] - 1].printInfo());
                                returns[0] = beverages[selections[0] - 1].printSelect(); // 첫 번째 선택한 음료 정보 저장
                                returns[1] = beverages[selections[1] - 1].printSelect(); // 두 번째 선택한 음료 정보 저장
                                break;
                            } else {
                                printlnWithDelay("유효한 숫자를 입력해주세요.");
                            }
                        } catch (NumberFormatException e) {
                            printlnWithDelay("숫자를 입력해주세요.");
                        }
                    } else {
                        printlnWithDelay("두 개의 숫자를 공백으로 구분하여 입력해주세요.");
                    }
                }
                break;
            }
        } while (true); // 무한 루프 (selectNum == 2일 경우 사용자로부터 올바른 입력을 받을 때까지 반복)

        return returns; // 선택한 음료 정보를 반환
    }
}
