package com.hello;

import com.order.BeverageOrderHandler;
import com.order.HandleMenu;
import com.order.PopcornOrderHandler;
import com.order.SetMenuHandler;
import com.order.SnackOrderHandler;

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
                    handler = new SetMenuHandler(
                            selectCombo,
                            selectPopCorn,
                            selectHalfPopCorn,
                            selectBeverage,
                            scanner
                    );
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
