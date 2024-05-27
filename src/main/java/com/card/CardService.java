package com.card;

public class CardService {
    private boolean isMaintenance; // 카드사 점검 상태
    private CardInfoManager cardInfoManager; // 카드 정보 관리 객체

    public CardService() {
        this.isMaintenance = false; // 카드사 점검 상태 - 초기 점검 상태는 false
        this.cardInfoManager = new CardInfoManager(); // 카드 정보 관리 객체 생성
        startMaintenanceCycle(); // 점검 주기 시작
    }

    // 카드사 점검 주기를 시작하는 메서드
    // new Thread(() -> { // 새로운 스레드 생성
    // 위 코드 부분에서 () -> {}  부분이 run() 메서드 실행한 것과 같은 효과입니다~
    private void startMaintenanceCycle(){
        final int MAINTENANCE_CYCLE = 60000; // 점검 주기: 1분
        final int MAINTENANCE_DURATION = 60000; // 점검 시간: 1분
        new Thread(() -> { // 새로운 스레드 생성
            while (true) { // 무한 루프
                try {
                    Thread.sleep(MAINTENANCE_CYCLE); // 2분 대기
                    isMaintenance = true; // 점검 상태로 설정
                    System.out.println("");
                    System.out.println("모든 카드사가 점검 중입니다. (1분간)"); // 점검 시작 메시지 출력
                    System.out.println("");
                    Thread.sleep(MAINTENANCE_DURATION); // 1분 대기
                    isMaintenance = false; // 점검 상태 해제
                    System.out.println("");
                    System.out.println("모든 카드사가 점검을 완료했습니다."); // 점검 완료 메시지 출력
                    System.out.println("");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // 스레드 인터럽트 상태 복원
                    e.printStackTrace();
                }
            }
        }).start(); // 스레드 시작
    }

    // 결제 처리 메서드
    public boolean processPayment(String cardNumber, int amount) {
        if (isMaintenance) { // 카드사 점검 중일 경우
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
    public boolean isMaintenance() {
        return isMaintenance; // 현재 점검 상태 반환
    }


}
