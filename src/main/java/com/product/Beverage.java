package com.product;

import java.util.HashMap;
import java.util.Map;

public class Beverage extends Product {
    public Beverage(String name, int price, String size) {
        super(name, price, size);
    }

    public Beverage(String name, int price) {
        super(name, price);
    }

    public Beverage(String name, int price, String size, int plusPrice) {
        super(name, price, size, plusPrice);
    }

    public Beverage(String name, int price, int plusPrice) {
        super(name, price, plusPrice);
    }

    public String printSetMenu(int price2) {
        int plusPrice = getPrice() - price2;
        setPlusPrice(plusPrice);
        if (plusPrice > 0) {
            return getName() + " " + getSize() + " (+" + plusPrice + "원)";
        } else if (plusPrice < 0) {
            return null;
        }
        return getName() + " " + getSize();
    }

    public Map<String, String> printSelect() {
        Map<String, String> beverageInfo = new HashMap<>();
        beverageInfo.put("order", "선택하신 음료 : " + getName());
        beverageInfo.put("name", getName());
        beverageInfo.put("size", getSize());
        beverageInfo.put("price", String.valueOf(getPlusPrice()));
        return beverageInfo;
    }

    public String printMenu() {
        return getName() + " " + getPrice() + "원";
    }
}
