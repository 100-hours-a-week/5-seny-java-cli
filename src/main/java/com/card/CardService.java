package com.card;

public class CardService {
//    private boolean isMaintenance; // 카드사 점검 상태
    private CardInfoManager cardInfoManager; // 카드 정보 관리 객체
    private BankInfoManager bankInfoManager = new BankInfoManager(); // 은행 점검정보 관리 객체
    private String bankName = "국민카드"; // 현재는 카드사 이름 하나로만 설정

    public CardService() {
//        this.isMaintenance = false; // 카드사 점검 상태 - 초기 점검 상태는 false
        this.cardInfoManager = new CardInfoManager(); // 카드 정보 관리 객체 생성
    }

    // 결제 처리 메서드
    public boolean processPayment(String cardNumber, int amount) {
        if (bankInfoManager.getBankInfo(bankName).isMaintenance()) { // 카드사 점검 중일 경우
            System.out.println( "카드사가 점검 중입니다. 잠시 후 다시 시도해주세요."); // 점검 중 메시지 출력
            return false; // 결제 실패
        }
        CardInfoManager.Card card = cardInfoManager.getCardInfo(cardNumber); // 카드 정보 가져오기
        if (card != null && card.getCardNumber().equals(cardNumber)) {
            int balance = card.getBalance() - amount;
            if (balance < 0) {
                System.out.println("잔액이 부족합니다."); // 잔액 부족 메시지 출력
                return false; // 결제 실패
            }
            System.out.println("결제가 완료되었습니다. 남은 잔액: " + balance + "원"); // 결제 완료 메시지 출력
            return cardInfoManager.updateCardBalance(card.getCardNumber(), amount); // 잔액 업데이트 시도
        } else {
            System.out.println("카드정보가 존재하지 않습니다."); // 유효하지 않은 카드 메시지 출력
            return false; // 결제 실패
        }
    }
//    public boolean isMaintenance() {
//        return bankInfoManager.getBankInfo(bankName).isMaintenance(); // 현재 점검 상태 반환
//    }


}
