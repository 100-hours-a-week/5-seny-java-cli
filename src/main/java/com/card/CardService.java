package com.card;

import com.payment.PaymentProcessRun;

public class CardService {
//    private boolean isMaintenance; // 카드사 점검 상태
    private CardInfoManager cardInfoManager; // 카드 정보 관리 객체
    private BankInfoManager bankInfoManager = new BankInfoManager(); // 은행 점검정보 관리 객체
    private PaymentInfoManager paymentInfoManager = new PaymentInfoManager(); // 결제 정보 관리 객체
    private String bankName = "국민카드"; // 현재는 카드사 이름 하나로만 설정
    private int paymentId; // 결제 아이디

    public CardService() {
        this.cardInfoManager = new CardInfoManager(); // 카드 정보 관리 객체 생성
    }

    // 결제 처리 메서드
    public boolean processPayment(String cardNumber, int amount, int paymentId) {
        this.paymentId = paymentId; // 결제 아이디 설정

        // 결제 스레드 생성
        Thread paymentThread = new Thread(new PaymentProcessRun(
                cardNumber, amount, paymentId
        ));

        paymentThread.start(); // 결제 스레드 시작

        if (bankInfoManager.getBankInfo(bankName).isMaintenance()) { // 카드사 점검 중일 경우
            System.out.println("카드사가 점검 중입니다. 잠시 후 다시 시도해주세요."); // 점검 중 메시지 출력
            paymentInfoManager.updatePaymentStatus(paymentId, 2); // 2: 점검중으로 상태 업데이트
            return false; // 결제 실패
        }

        if (paymentInfoManager.getPaymentInfo(paymentId).getStatus() == 1) { // 결제가 성공하면
            return true; // 결제 성공 반환
        }
        return false;
    }

}
