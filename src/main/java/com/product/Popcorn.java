package com.product;

public class Popcorn extends Product {
    public Popcorn(String name, int price, String size) {
        super(name, price, size);
    }

    public Popcorn(String name, int price) {
        super(name, price);
    }

    public Popcorn(String name, int price, String size, int plusPrice) {
        super(name, price, size, plusPrice);
    }

    public Popcorn(String name, int price, int plusPrice) {
        super(name, price, plusPrice);
    }

    public String printMenu() {
        if (getPlusPrice() > 0) {
            return getName() + " " + getSize() + " (+" + getPlusPrice() + "원)";
        }
        return getName() + " " + getSize() + " ";
    }
}
