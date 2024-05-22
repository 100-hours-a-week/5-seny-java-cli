package com.product;

public class BeverageList {
    // 음료 객체 반환
    public static Beverage[] createBeverageList(){
        Beverage[] beverages = new Beverage[10];
        beverages[0] = new Beverage("셀프 탄산", 2500, "M");
        beverages[1] = new Beverage("셀프 탄산", 3000,  "L", 500);
        beverages[2] = new Beverage("아이스티", 4000, "M", 1500);
        beverages[3] = new Beverage("아이스티", 5000, "L", 2500);
        beverages[4] = new Beverage("오렌지 에이드", 5000, 2500);
        beverages[5] = new Beverage("자몽 에이드", 5000, 2500);
        beverages[6] = new Beverage("핫 아메리카노", 3000, 500);
        beverages[7] = new Beverage("아이스 아메리카노", 3500, 1000);
        beverages[8] = new Beverage("디카페인 콜드브루", 4000, 1500);
        beverages[9] = new Beverage("그때그커피", 4400, 1900);

        return beverages;
    }

}
