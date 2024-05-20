package com.hello;
import com.order.Order;
import com.product.Popcorn;
import com.product.PopcornList;

import java.util.Scanner;

public class SelectPopCorn {
    private int input;
    // PopcornList에서 팝콘 객체 배열 가져오기
    private Popcorn[] popcorns = PopcornList.createPopcornList();

    public int run(){
        Scanner scanner = new Scanner(System.in); // 사용자 입력을 위한 Scanner 객체 생성
        do {
            // 팝콘 메뉴 출력
            for (int i = 0; i < popcorns.length; i++) {
                System.out.println((i + 1) + ": " + popcorns[i].printMenu());
            }
            System.out.println("0: 종료");
            System.out.println("맛을 선택해주세요: ");

            // 사용자로부터 다음 팝콘을 선택하도록 숫자 입력 받기
            input = scanner.nextInt();

            // 입력한 번호에 해당하는 팝콘 정보 출력 (예외 처리는 하지 않음)
            if (input > 0 && input <= popcorns.length) {
                System.out.println("선택하신 팝콘: " + popcorns[input - 1].printMenu());
                break;
            } else if (input == 0) {
                System.out.println("프로그램을 종료합니다.");
                break;
            } else {
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            }

        } while (input != 0); // 입력 받은 값이 0이 아닐 때까지 반복

        return input;
    }

}
