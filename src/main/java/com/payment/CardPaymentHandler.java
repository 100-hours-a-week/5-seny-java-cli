package com.payment;

import com.card.BankInfoManager;
import com.card.CardService;
import com.card.PaymentInfoManager;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class CardPaymentHandler implements PaymentHandler {
    private CardService cardService; // 카드 서비스 객체
    PaymentInfoManager paymentInfoManager = new PaymentInfoManager(); // 결제 정보 관리 객체
    BankInfoManager bankInfoManager = new BankInfoManager(); // 은행 정보 관리 객체
    private String bankName = "국민카드"; // 현재는 카드사 이름 하나로만 설정

    // 생성자: 카드 서비스 객체를 설정합니다.
    public CardPaymentHandler(CardService cardService) {
        this.cardService = cardService; // 카드 서비스 설정
    }

    // 금액을 받아서 결제를 처리하는 메서드입니다.
    @Override
    public boolean processPayment(int amount) {
        Scanner scanner = new Scanner(System.in); // 사용자 입력을 받기 위한 Scanner 객체 생성
        AtomicBoolean paymentProcessed = new AtomicBoolean(false); // 결제 처리 여부를 저장하는 AtomicBoolean
        int paymentId = paymentInfoManager.addPayment(amount); // 결제 정보를 추가하고 결제 아이디를 반환합니다.
        System.out.println("결제 아이디: " + paymentId); // 결제 아이디 출력
        if (bankInfoManager.getBankInfo(bankName).isMaintenance()){
            System.out.println("카드사가 점검 중입니다. 잠시 후 다시 시도해주세요.");
            paymentInfoManager.updatePaymentStatus(paymentId, 2);
            return false;
        }

        bankInfoManager.getBankInfo(bankName).setMaintenance(true); // 카드사 점검 상태를 true로 설정합니다.
        paymentInfoManager.updatePaymentStatus(paymentId, 0);
        System.out.print("카드 번호를 입력하세요: ");
        String cardNumber = scanner.nextLine(); // 카드 번호 입력 받기

        boolean result = cardService.processPayment(cardNumber, amount, paymentId); // 카드 서비스를 이용해 결제를 처리합니다.
//        paymentInfoManager.updatePaymentStatus(paymentId, result ? 1 : 0); // 결제 상태를 업데이트합니다.
        int status = paymentInfoManager.getPaymentInfo(paymentId).getStatus();
        if (status == 1) { // 결제가 성공하고 상태가 1(결제 완료)인 경우 - 이중체크
            paymentProcessed.set(true); // 결제가 성공하면 결제 처리 여부를 true로 설정합니다.
        }


        return paymentProcessed.get();
    }
}
