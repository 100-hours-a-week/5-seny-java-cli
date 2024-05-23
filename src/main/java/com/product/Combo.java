package com.product;

public class Combo extends Product {
    public Combo(String name, int price, String[] info) {
        super(name, price, info);
    }

    public String printSetMenu() {
        String[] info = getInfo();
        String popcornInfo = info[0] + info[1] + info[2];
        String drinkInfo = info[3] + info[4] + info[5];
        // 출력예시 : 싱글세트 (팝콘M1+탄산M1)
        return getName() + " (" + popcornInfo + "+ " + drinkInfo + ")";
    }
}