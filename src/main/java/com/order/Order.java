package com.order;

import com.product.Popcorn;
import com.product.Snack;

public class Order {
    public int price1;
    public int price2;
    public int price3;
    public int sum;
    public Popcorn popcorn;
    public Snack snack;


    public Order(Popcorn popcorn) {
        this.price1 = popcorn.getPrice();
        this.price2 = 0;
        this.price3 = 0;
        this.popcorn = popcorn;

    }

    public Order(Popcorn popcorn, Snack snack){
        this.price1 = popcorn.getPrice();
        this.price2 = snack.getPrice();
        this.price3 = 0;
        this.popcorn = popcorn;
        this.snack = snack;
    }

    public String totalPrice() {
        sum = price1 + price2 + price3;
        return "결제하실 금액은 총 " +  sum + "원 입니다.";
    }

    public String totalOrder() {
        if (this.price2 == 0 ){
            return "주문하신 메뉴는 " + popcorn.getName() + " " + popcorn.getSize() + "입니다.";
        }
        else {
            return "주문하신 메뉴는 " + popcorn.getName() + " " + popcorn.getSize()  + ", " + snack.getName() + "입니다.";
        }
    }

}
