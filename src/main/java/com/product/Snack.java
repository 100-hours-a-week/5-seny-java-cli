package com.product;

public class Snack extends Product{
    public Snack(String name, int price, String size) {
        super(name, price, size);
    }

    public Snack(String name, int price) {
        super(name, price);
    }

    public Snack(String name, int price, String size, int plusPrice) {
        super(name, price, size, plusPrice);
    }

    public Snack(String name, int price, int plusPrice) {
        super(name, price, plusPrice);
    }

    public String printSetMenu(){
        return getName() + " (+" + getPrice() + "원) ";
    }

    @Override
    public String printInfo() {
        return getName() + " " ;
    }
}
