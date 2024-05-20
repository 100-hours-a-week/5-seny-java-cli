package com.order;

import com.product.Popcorn;

public class Order {
    public int price;

    public Order(Popcorn popcorn) {
        this.price = popcorn.getPrice();
    }

    public String totalPrice() {
        return "결제하실 금액은 총 " + this.price + "원 입니다.";
    }

}
