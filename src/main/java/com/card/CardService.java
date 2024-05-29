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

        // 스레드가 인터럽트되었는지 확인
        try {
            paymentThread.join(10 * 1000); // 10초 동안 스레드 대기
            if (paymentThread.isAlive()) {
                paymentThread.interrupt(); // 스레드가 아직 실행 중이면 인터럽트
                // 인터럽트가 발생하면 결제 실패 처리하고 메서드 종료
                paymentInfoManager.updatePaymentStatus(paymentId, 3); // 3: 시간 초과로 상태 업데이트
                System.out.println("\n결제 유효시간이 초과되었습니다.");
                return false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (bankInfoManager.getBankInfo(bankName).isMaintenance()) { // 카드사 점검 중일 경우
            System.out.println("카드사가 점검 중입니다. 잠시 후 다시 시도해주세요."); // 점검 중 메시지 출력
            paymentInfoManager.updatePaymentStatus(paymentId, 2); // 2: 점검중으로 상태 업데이트
            return false; // 결제 실패
        }

        CardInfoManager.Card card = cardInfoManager.getCardInfo(cardNumber); // 카드 정보 가져오기
        if (card != null && card.getCardNumber().equals(cardNumber)) { // 카드 정보가 존재하고 카드 번호가 일치할 경우
            int balance = card.getBalance() - amount;
            if (balance < 0) {
                System.out.println("잔액이 부족합니다."); // 잔액 부족 메시지 출력
                paymentInfoManager.updatePaymentStatus(paymentId, 0); // 0: 잔액부족 결제실패
                return false; // 결제 실패
            }

            System.out.println("결제가 완료되었습니다. 남은 잔액: " + balance + "원"); // 결제 완료 메시지 출력
            paymentInfoManager.updatePaymentStatus(paymentId, 1); // 1: 결제완료로 상태 업데이트
            return cardInfoManager.updateCardBalance(card.getCardNumber(), amount); // 잔액 업데이트 시도

        } else if (!bankInfoManager.getBankInfo(bankName).isMaintenance()) { // 카드 정보가 존재하지 않을 경우 & 점검중 아닐때만
            System.out.println("카드정보가 존재하지 않습니다."); // 유효하지 않은 카드 메시지 출력
            paymentInfoManager.updatePaymentStatus(paymentId, 0); // 0: 결제실패
            return false; // 결제 실패
        }
        return false;
    }
//    public boolean isMaintenance() {
//        return bankInfoManager.getBankInfo(bankName).isMaintenance(); // 현재 점검 상태 반환
//    }


}
