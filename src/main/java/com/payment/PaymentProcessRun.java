package com.payment;

import com.card.BankInfoManager;
import com.card.CardInfoManager;
import com.card.CardService;
import com.card.PaymentInfoManager;

public class PaymentProcessRun implements Runnable{
    private final String cardNumber;
    private final int paymentId;
    private final int amount;
    private CardService cardService = new CardService();
    private BankInfoManager bankInfoManager = new BankInfoManager();
    private PaymentInfoManager paymentInfoManager = new PaymentInfoManager();
    private String bankName = "국민카드"; // 현재는 카드사 이름 하나로만 설정
    private CardInfoManager cardInfoManager = new CardInfoManager(); // 카드 정보 관리 객체


    /**
     * 결제처리 서비스
     * @param cardNumber : 고객의 카드넘버
     * @param paymentId : payment.json (가제) 에 저장되어 있는 결제 아이디
     * @param amount : 결제 금액
     */
    public  PaymentProcessRun(String cardNumber, int amount, int paymentId) {
        this.paymentId = paymentId;
        this.cardNumber = cardNumber;
        this.amount = amount;
    }

    @Override
    public void run() {

        // JSON 파일에서 paymentId 를 찾아서 결제 상태를 확인하고, 결제가 완료되었으면 return true
        if (bankInfoManager.getBankInfo(bankName).isMaintenance()) { // 점검 상태 확인
            // payment.json 정상처리가 되지 않았음을 남김 (paymentId 기준으로)
//            System.out.println("\n카드사가 현재 점검 중입니다.");
            // 콜러에서 안내 메시지를 출력하도록 변경
            return;
        }

        CardInfoManager.Card card = cardInfoManager.getCardInfo(cardNumber);
        if (card != null && card.getCardNumber().equals(cardNumber)) {
            int balance = card.getBalance() - amount;
            if (balance < 0) {
                System.out.println("잔액이 부족합니다.");
                paymentInfoManager.updatePaymentStatus(paymentId, 0);
                return;
            }
            System.out.println("결제가 완료되었습니다. 남은 잔액: " + balance + "원"); // 결제 완료 메시지 출력
            paymentInfoManager.updatePaymentStatus(paymentId, 1);
            cardInfoManager.updateCardBalance(card.getCardNumber(), amount);

        } else if (!bankInfoManager.getBankInfo(bankName).isMaintenance()) {
            System.out.println("카드정보가 존재하지 않습니다.");
            paymentInfoManager.updatePaymentStatus(paymentId, 0);
        }

    }
}
