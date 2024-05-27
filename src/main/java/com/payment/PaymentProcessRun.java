package com.payment;

import com.card.CardService;

public class PaymentProcessRun implements Runnable{
    private final String cardNumber;
    private final int paymentId;
    private final int amount;
    private CardService cardService = new CardService();
    /**
     * 결제처리 서비스
     * @param cardNumber : 고객의 카드넘버
     * @param paymentId : payment.json (가제) 에 저장되어 있는 결제 아이디
     * @param amount : 결제 금액
     */
    public  PaymentProcessRun(String cardNumber, int paymentId, int amount) {
        this.paymentId = paymentId;
        this.cardNumber = cardNumber;
        this.amount = amount;
    }

    @Override
    public void run() {

        if (cardService.isMaintenance()) { // 점검 상태 확인
            // TODO: payment.json 정상처리가 되지 않았음을 남김 (paymentId 기준으로)
            System.out.println("\n카드사가 현재 점검 중입니다.");
            return;
        }

        if (cardService.processPayment(cardNumber, amount)) {
            // TODO: payment.json 파일에 paymentId 를 찾아서 "정상" 처리 표기하도록 수정
            System.out.println("결제가 완료되었습니다.");
        } else {
            System.out.println("\n유효한 카드가 아니거나 잔액이 부족합니다.");
        }

    }
}
