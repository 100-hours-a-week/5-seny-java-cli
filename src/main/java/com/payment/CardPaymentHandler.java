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
        AtomicBoolean timeout = new AtomicBoolean(false); // 시간 초과 여부를 저장하는 AtomicBoolean

        Thread paymentThread = new Thread(() -> {
            System.out.print("카드 번호를 입력하세요: ");
            String cardNumber = scanner.nextLine(); // 카드 번호 입력 받기

            if (cardService.isMaintenance()) { // 점검 상태 확인
                System.out.println("\n카드사가 현재 점검 중입니다.");
                return;
            }

            if (cardService.processPayment(cardNumber, amount)) {
                paymentProcessed.set(true); // 결제 완료 상태 설정
                System.out.println("결제가 완료되었습니다.");
            } else {
                System.out.println("\n유효한 카드가 아니거나 잔액이 부족합니다.");
            }
        });

        paymentThread.start(); // 결제 스레드 시작

        try {
            paymentThread.join(10 * 1000); // 10초 동안 스레드 대기
            if (paymentThread.isAlive()) {
                paymentThread.interrupt(); // 스레드가 아직 실행 중이면 인터럽트
                timeout.set(true); // 시간 초과 상태 설정
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (timeout.get() && !paymentProcessed.get() && !cardService.isMaintenance()) { // 결제 시간이 초과된 경우 + 결제가 완료되지 않은 경우 + 카드사 점검 중이 아닌 경우
            System.out.println("\n결제 유효시간이 초과되었습니다.");
        }

        return paymentProcessed.get();
    }
}
