package com.product;

public class Snack extends Product{
    public Snack(String name, int price) {
        super(name, price);
    }

    public String printSetMenu(){
        return getName() + " (+" + getPrice() + "원) ";
    }

    @Override
    public String printInfo() {
        return getName() + " " ;
    }
}
