package com.product;

public class Product {
    private String name;
    private int price;
    private String size;
    private int plusPrice = 0;
    private String[] info;

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

    // 세트메뉴에서 사용
    public Product(String name, int price, String[] info){
        this.name = name;
        this.price = price;
        this.info = info;
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

    public String[] getInfo() {
        return info;
    }

    public String printSetMenu(int price) {
        int plusPrice = price - getPlusPrice();
        if (getPlusPrice() > 0) {
            return getName() + " " + getSize() + " (+" + plusPrice + "원)";
        }
        return getName() + " " + getSize() + " ";
    }

    public String printInfo(){
        return name + " " + size + " ";
    }

    public String printMenu() {
        return name + " " + price + "원 ";
    }

}
