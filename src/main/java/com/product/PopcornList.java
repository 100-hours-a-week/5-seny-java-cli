package com.product;

public class PopcornList {
    // 팝콘 객체 배열 반환
    public static Popcorn[] createPopcornList() {
        Popcorn[] popcorns = new Popcorn[9];
        popcorns[0] = new Popcorn("고소팝콘", 5000, "M");
        popcorns[1] = new Popcorn("달콤팝콘", 6000, "M", 1000);
        popcorns[2] = new Popcorn("더블치즈팝콘", 6000, "M", 1000);
        popcorns[3] = new Popcorn("바질어니언팝콘", 6000, "M", 1000);
        popcorns[4] = new Popcorn("고소팝콘", 5500, "L", 500);
        popcorns[5] = new Popcorn("달콤팝콘", 6500, "L", 1500);
        popcorns[6] = new Popcorn("더블치즈팝콘", 6500, "L", 1500);
        popcorns[7] = new Popcorn("바질어니언팝콘", 6500, "L", 1500);
        popcorns[8] = new Popcorn("반반팝콘", 6500, "L", 1500);



        return popcorns;
    }
}
