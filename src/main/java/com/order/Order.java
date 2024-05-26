package com.order;

import com.product.Beverage;
import com.product.Popcorn;
import com.product.Snack;

public class Order {
    private int totalPrice;
    private StringBuilder orderDetails;
    private Popcorn popcorn;
    private String popcornN1;
    private String popcornN2;
    private Snack snack;
    private Beverage beverage;

    public Order(Popcorn popcorn) {
        this.totalPrice = popcorn.getPrice();
        this.popcorn = popcorn;
        this.orderDetails = new StringBuilder();
        this.orderDetails.append("주문하신 메뉴는 ").append(popcorn.getName()).append(" ").append(popcorn.getSize()).append(" ").append(totalPrice).append("원입니다.\n");
    }

    public Order(Popcorn popcorn, int num1, int num2) {
        this.totalPrice = popcorn.getPrice();
        this.popcorn = popcorn;
        String[] popcornNames = {"고소", "달콤", "더블치즈", "바질어니언"};
        this.popcornN1 = popcornNames[num1 - 1];
        this.popcornN2 = popcornNames[num2 - 1];
        this.orderDetails = new StringBuilder();
        this.orderDetails.append("주문하신 메뉴는 반반팝콘(").append(popcornN1).append("+").append(popcornN2).append(") ").append(totalPrice).append("원입니다.\n");
    }

    public Order(Snack snack) {
        this.totalPrice = snack.getPrice();
        this.snack = snack;
        this.orderDetails = new StringBuilder();
        this.orderDetails.append("주문하신 메뉴는 ").append(snack.getName()).append(" 입니다.\n");
    }

    public Order(Beverage beverage) {
        this.totalPrice = beverage.getPrice();
        this.beverage = beverage;
        this.orderDetails = new StringBuilder();
        this.orderDetails.append("주문하신 메뉴는 ").append(beverage.getName()).append(" ").append(beverage.getSize()).append(" 입니다.\n");
    }

    public Order(int initialPrice) {
        this.totalPrice = initialPrice;
        this.orderDetails = new StringBuilder();
    }

    public void addItem(String name, int price, String size) {
        totalPrice += price;
        orderDetails.append(name).append(" : +").append(price).append("원 추가되었습니다.\n");
        orderDetails.append(name).append(" ").append(size).append(" : +").append(price).append("원 추가되었습니다.\n");
    }

    // 반반팝콘은 사이즈 출력 필요없음
    public void addItem(String name, int price) {
        totalPrice += price;
        orderDetails.append(name).append(" : +").append(price).append("원 추가되었습니다.\n");
        orderDetails.append(name).append(" : +").append(price).append("원 추가되었습니다.\n");
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public String getOrderDetails() {
        return orderDetails.toString();
    }

    public String totalPrice() {
        return "결제하실 금액은 총 " + totalPrice + "원 입니다.";
    }

    public String totalOrder() {
        return orderDetails.toString();
    }
}
