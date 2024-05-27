package com.payment;

import com.card.CardService;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class CardPaymentHandler implements PaymentHandler {
    private CardService cardService; // 카드 서비스 객체

    // 생성자: 카드 서비스 객체를 설정합니다.
    public CardPaymentHandler(CardService cardService) {
        this.cardService = cardService; // 카드 서비스 설정
    }

    // 금액을 받아서 결제를 처리하는 메서드입니다.
    @Override
    public boolean processPayment(int amount) {
        Scanner scanner = new Scanner(System.in); // 사용자 입력을 받기 위한 Scanner 객체 생성
        AtomicBoolean paymentProcessed = new AtomicBoolean(false); // 결제 처리 여부를 저장하는 AtomicBoolean
        System.out.print("카드 번호를 입력하세요: ");
        String cardNumber = scanner.nextLine(); // 카드 번호 입력 받기

        // paymentId 를 추가하는 로직 추가 -- 시작

        // some code...

        // paymentId 를 추가하는 로직 추가 -- 종료

        Thread paymentThread = new Thread(new PaymentProcessRun(
                cardNumber, 1 /* 이 부분은 수정*/, amount
        ));

        paymentThread.start(); // 결제 스레드 시작

        try {
            paymentThread.join(10 * 1000); // 10초 동안 스레드 대기
            if (paymentThread.isAlive()) {
                paymentThread.interrupt(); // 스레드가 아직 실행 중이면 인터럽트
                System.out.println("\n결제 유효시간이 초과되었습니다.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return paymentProcessed.get();
    }
}
