package com.hello;

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
        try {
            Welcome welcome = new Welcome();
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
            }

            if (handler != null) {
                handler.handle();
                Order order = handler.getOrder();

                System.out.println("결제 방식을 선택해주세요: 1. 카드결제 2. 현금결제");
                int paymentMethod = scanner.nextInt();
                PaymentHandler paymentHandler = null;

                while (paymentMethod != 1 && paymentMethod != 2) {
                    System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
                    paymentMethod = scanner.nextInt();
                }
                if (paymentMethod == 1) {
                    paymentHandler = new CardPaymentHandler();
                } else if (paymentMethod == 2) {
                    paymentHandler = new CashPaymentHandler(scanner);
                }

                paymentHandler.processPayment(order.getTotalPrice());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
