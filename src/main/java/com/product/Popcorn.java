package com.product;

import java.util.HashMap;
import java.util.Map;

public class Popcorn extends Product {
    public Popcorn(String name, int price, String size) {
        super(name, price, size);
    }

    public Popcorn(String name, int price, String size, int plusPrice) {
        super(name, price, size, plusPrice);
    }

    public String printSetMenu(int price2) {
        int plusPrice = getPrice() - price2;
        setPlusPrice(plusPrice);
        if (plusPrice > 0) {
            return getName() + " " + getSize() + " (+" + plusPrice + "원)";
        }
        else if (plusPrice < 0){
            return null;
        }
        return getName() + " " + getSize() + " ";
    }

    public Map<String, String> printSelect() {
        Map<String, String> beverageInfo = new HashMap<>();
        beverageInfo.put("order", "선택하신 팝콘 : " + getName());
        beverageInfo.put("name", getName());
        beverageInfo.put("size", getSize());
        beverageInfo.put("price", String.valueOf(getPlusPrice()));
        return beverageInfo;
    }

    @Override
    public String printMenu() {
        return getName() + " " + getSize() +" " + getPrice() + "원 ";

    }
}
