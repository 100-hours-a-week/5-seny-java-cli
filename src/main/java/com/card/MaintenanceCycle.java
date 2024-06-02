package com.card;
// 카드사 점검 주기를 시작하는 메서드
// new Thread(() -> { // 새로운 스레드 생성
// 위 코드 부분에서 () -> {}  부분이 run() 메서드 실행한 것과 같은 효과입니다~
public class MaintenanceCycle implements Runnable {
    private static final int MAINTENANCE_CYCLE = 60000; // 점검 주기: 1분
    private static final int MAINTENANCE_DURATION = 60000; // 점검 시간: 1분
    private BankInfoManager bankInfoManager;

    public MaintenanceCycle(BankInfoManager bankInfoManager) {
        this.bankInfoManager = bankInfoManager;
    }

    @Override
    public void run() {
        while (true) { // 무한 루프
            try {
                bankInfoManager.updateBankMaintenance("국민카드", false); // 초기 상태 설정
                bankInfoManager.updateBankMaintenance("우리카드", false); // 초기 상태 설정
                Thread.sleep(MAINTENANCE_CYCLE); // 2분 대기
                bankInfoManager.updateBankMaintenance("국민카드", true); // 점검 상태로 설정
                bankInfoManager.updateBankMaintenance("우리카드", true); // 점검 상태로 설정
                System.out.println("");
                System.out.println("모든 카드사가 점검 중입니다. (1분간)"); // 점검 시작 메시지 출력
                System.out.println("");
                Thread.sleep(MAINTENANCE_DURATION); // 1분 대기
                bankInfoManager.updateBankMaintenance("국민카드", false); // 점검 상태 해제
                bankInfoManager.updateBankMaintenance("우리카드", false); // 점검 상태 해제
                System.out.println("");
                System.out.println("모든 카드사가 점검을 완료했습니다."); // 점검 완료 메시지 출력
                System.out.println("");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // 스레드 인터럽트 상태 복원
                e.printStackTrace();
            }
        }
    }
}