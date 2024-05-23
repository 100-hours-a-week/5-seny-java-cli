package com.product;

public class ComboList {
    // 콤보 객체 배열 반환
    public static Combo[] createComboList() {
        Combo[] combos = new Combo[4];
        combos[0] = new Combo("스몰 세트", 7000, new String[]{"팝콘", "M", "1", "탄산", "M" ,"1", "5000", "2500" });
        combos[1] = new Combo("CGV 콤보", 10000, new String[]{"팝콘", "L", "1", "탄산", "M" ,"2", "5500", "2500" });
        combos[2] = new Combo("더블 콤보", 13000, new String[]{"팝콘", "M", "2", "탄산", "M" ,"2", "5000", "2500" });
        combos[3] = new Combo("라지 콤보", 15000, new String[]{"팝콘", "L", "2", "탄산", "L" ,"2", "5000", "3000"});
        return combos;
    }
}
