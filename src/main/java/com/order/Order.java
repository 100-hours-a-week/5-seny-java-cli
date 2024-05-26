package com.order;

import com.product.Beverage;
import com.product.Popcorn;
import com.product.Snack;

public class Order {
    public int price1;
    public int price2;
    public int price3;
    public int sum;
    public Popcorn popcorn;
    public String popcornN1;
    public String popcornN2;
    public Snack snack;
    public Beverage beverage;

    public Order(int price) {
        this.sum = price;
    }

    public Order(Popcorn popcorn) {
        this.price1 = popcorn.getPrice();
        this.popcorn = popcorn;
    }

    public Order(Popcorn popcorn, int num1, int num2) {
        this.price1 = popcorn.getPrice();
        this.popcorn = popcorn;
        String[] popcornNames= {"고소", "달콤", "더블치즈", "바질어니언"};
        this.popcornN1 = popcornNames[num1 - 1];
        this.popcornN2 = popcornNames[num2 - 1];

    }

    public Order(Snack snack) {
        this.price1 = snack.getPrice();
        this.snack = snack;
    }

    public Order(Beverage beverage){
        this.price1 = beverage.getPrice();
        this.beverage = beverage;
    }

    public Order(Popcorn popcorn, Snack snack){
        this.price1 = popcorn.getPrice();
        this.price2 = snack.getPrice();
        this.price3 = 0;
        this.popcorn = popcorn;
        this.snack = snack;
    }

    public int getPrice1() {
        return price1;
    }

    public int getPrice2() {
        return price2;
    }

    public int getPrice3() {
        return price3;
    }

    public int getSum() {
        return sum;
    }

    public Popcorn getPopcorn() {
        return popcorn;
    }

    public String getPopcornN1() {
        return popcornN1;
    }

    public String getPopcornN2() {
        return popcornN2;
    }

    public Snack getSnack() {
        return snack;
    }

    public Beverage getBeverage() {
        return beverage;
    }

    public void setPrice1(int price1) {
        this.price1 = price1;
    }

    public void setPrice2(int price2) {
        this.price2 = price2;
    }

    public void setPrice3(int price3) {
        this.price3 = price3;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public void setPopcorn(Popcorn popcorn) {
        this.popcorn = popcorn;
    }

    public void setPopcornN1(String popcornN1) {
        this.popcornN1 = popcornN1;
    }

    public void setPopcornN2(String popcornN2) {
        this.popcornN2 = popcornN2;
    }

    public void setSnack(Snack snack) {
        this.snack = snack;
    }

    public void setBeverage(Beverage beverage) {
        this.beverage = beverage;
    }

    public String totalPrice() {
        sum = price1 + price2 + price3;
        return "결제하실 금액은 총 " +  sum + "원 입니다.";
    }

    public String totalOrder() {
        if (popcorn != null && snack == null && beverage == null) {
            if (popcornN1 != null && popcornN2 != null) {
                return "주문하신 메뉴는 반반팝콘("  + popcornN1 + "+"+ popcornN2 + ") "+ popcorn.getPrice()+ "원입니다.";
            }
            return "주문하신 메뉴는 " + popcorn.getName() + " " + popcorn.getSize() + " "+ popcorn.getPrice()+ "원입니다.";
        } else if (popcorn == null && snack != null && beverage != null) {
            return "주문하신 메뉴는 " + snack.getName() + " 입니다.";
        } else if (popcorn == null && snack == null && beverage != null) {
            return "주문하신 메뉴는 " + beverage.getName() + " " + beverage.getSize() + " 입니다.";
        }
        else if (popcorn != null && snack != null) {
            return "주문하신 메뉴는 " + popcorn.getName() + " " + popcorn.getSize() + ", " + snack.getName() + " 입니다.";
        } else {
            return "주문하신 메뉴가 없습니다.";
        }
    }

}
