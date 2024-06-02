package com.payment;
import java.util.Scanner;

public class CashPaymentHandler implements PaymentHandler{
    private Scanner scanner;

    public CashPaymentHandler(Scanner scanner) {
        this.scanner = scanner;
    }
    @Override
    public boolean processPayment(int amount) throws InterruptedException {
        System.out.println("결제 금액은 " + amount + "원 입니다.");
        System.out.println("현금을 투입해주세요.");
        int cash = scanner.nextInt();
        while (cash < amount) {
            System.out.println("투입 금액이 부족합니다. 현금을 더 투입해주세요.");
            cash += scanner.nextInt();
        }
        if (cash > amount) {
            System.out.println("거스름돈 " + (cash - amount) + "원을 반환합니다.");
        }
        Thread.sleep(2000);
        System.out.println("결제가 완료되었습니다.");
        return true;
    }
}
