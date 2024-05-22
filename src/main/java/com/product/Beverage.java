package com.product;

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

    public String printSetMenu() {
        if (getPlusPrice() > 0) {
            return getName() + " " + getSize() + " (+" + getPlusPrice() + "원)";
        }
        return getName() + " " + getSize() + " ";
    }
}
