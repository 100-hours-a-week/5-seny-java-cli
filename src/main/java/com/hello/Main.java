package com.hello;

import com.card.BankInfoManager;
import com.card.CardService;
import com.card.MaintenanceCycle;
import com.order.BeverageOrderHandler;
import com.order.HandleMenu;
import com.order.PopcornOrderHandler;
import com.order.SetMenuHandler;
import com.order.SnackOrderHandler;
import com.order.Order;
import com.payment.CardPaymentHandler;
import com.payment.CashPaymentHandler;
import com.payment.PaymentHandler;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        BankInfoManager bankInfoManager = new BankInfoManager();
        Thread maintenanceThread = new Thread(new MaintenanceCycle((bankInfoManager)));
        // 이렇게 하면 `MaintenanceCycle` 클래스가 점검 주기를 관리하는 역할을 담당
        maintenanceThread.start(); // 카드사 점검 주기 시작

        try {
            Welcome welcome = new Welcome();
            // 카드 서비스 객체 생성
            CardService cardService = new CardService();

            int menuSelection = welcome.run();
            System.out.println(menuSelection);

            HandleMenu handler = null;
            SelectCombo selectCombo = new SelectCombo(scanner);
            SelectPopCorn selectPopCorn = new SelectPopCorn(scanner);
            SelectHalfPopCorn selectHalfPopCorn = new SelectHalfPopCorn(scanner);
            SelectBeverage selectBeverage = new SelectBeverage(scanner);
            SelectSnack selectSnack = new SelectSnack(scanner);

            switch (menuSelection) {
                case 1:
                    handler = new SetMenuHandler(selectCombo, selectPopCorn, selectHalfPopCorn, selectBeverage, scanner);
                    break;
                case 2:
                    handler = new PopcornOrderHandler(selectPopCorn, selectHalfPopCorn);
                    break;
                case 3:
                    handler = new BeverageOrderHandler(selectBeverage);
                    break;
                case 4:
                    handler = new SnackOrderHandler(selectSnack);
                    break;
                default:
                    System.out.println("잘못된 선택입니다.");
                    return; // 잘못된 선택 시 프로그램 종료
            }

            if (handler != null) {
                handler.handle();
                Order order = handler.getOrder();
                boolean paymentSuccessful = false;

                while (!paymentSuccessful) {
                    System.out.println("결제 방식을 선택해주세요: 1. 카드결제 2. 현금결제");

                    int paymentMethod = 0;
                    if (scanner.hasNextInt()) {
                        paymentMethod = scanner.nextInt();
                        scanner.nextLine(); // 입력 버퍼를 비웁니다.
                    }

                    while (paymentMethod != 1 && paymentMethod != 2) {
                        System.out.println("\n잘못된 선택입니다. 다시 선택해주세요.");
                        if (scanner.hasNextInt()) {
                            paymentMethod = scanner.nextInt();
                            scanner.nextLine(); // 입력 버퍼를 비웁니다.
                        }
                    }

                    PaymentHandler paymentHandler = null;
                    if (paymentMethod == 1) {
                        paymentHandler = new CardPaymentHandler(cardService); // 카드 결제 핸들러 설정
                    } else if (paymentMethod == 2) {
                        paymentHandler = new CashPaymentHandler(scanner); // 현금 결제 핸들러 설정
                    }

                    paymentSuccessful = paymentHandler.processPayment(order.getTotalPrice()); // 결제 처리

                    if (paymentSuccessful) {
                        System.out.println("결제가 완료되었습니다.");
                        System.exit(0); // 프로그램 종료
                    } else {
                        System.out.println("결제가 실패했습니다. 다시 시도해 주세요.");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
