package com.product;

public class Product {
    private String name;
    private int price;
    private String size;
    private int plusPrice = 0;

    public Product(String name, int price, String size) {
        this.name = name;
        this.price = price;
        this.size = size;

    }

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
        this.size = "M";
    }

    public Product(String name, int price, String size, int plusPrice) {
        this.name = name;
        this.price = price;
        this.size = size;
        this.plusPrice = plusPrice;
    }

    public Product(String name, int price, int plusPrice) {
        this.name = name;
        this.price = price;
        this.size = "M";
        this.plusPrice = plusPrice;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getSize() {
        return size;
    }

    public int getPlusPrice() {
        return plusPrice;
    }

    public String printMenu() {
        if (plusPrice > 0) {
            return name + " " + price + "원 " + size + " (+" + plusPrice + "원)";
        }
        return name + " " + price + "원 " + size + " ";
    }

    public String info(){
        return name + " " + price + " " + size + " " + plusPrice;
    }


}
