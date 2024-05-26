package com.payment;

public class CardPaymentHandler implements PaymentHandler {

    @Override
    public void processPayment(int amount) throws InterruptedException {
        System.out.println("결제 금액은 " + amount + "원 입니다.");
        System.out.println("카드 결제를 진행합니다.");
        Thread.sleep(2000);
        System.out.println("결제가 완료되었습니다.");
    }
}
