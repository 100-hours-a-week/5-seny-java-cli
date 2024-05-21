package com.product;

public class SnackList {
    // 사이드 메뉴 객체 배열 반환
    public static Snack[] createSnackList() {
        Snack[] snacks = new Snack[10];
        snacks[0] = new Snack("한마리 통통 오징어", 8000);
        snacks[1] = new Snack("즉석 구이 오징어", 3500);
        snacks[2] = new Snack("칠리치즈 나초", 4000);
        snacks[3] = new Snack("갈릭 스노윙 핫도그", 4000);
        snacks[4] = new Snack("칠리치즈 핫도그", 4000);
        snacks[5] = new Snack("더블 치즈 오징어 튀김", 3500);
        snacks[6] = new Snack("팩 오징어", 2000);
        snacks[7] = new Snack("바삭 구운 먹태", 4000);
        snacks[8] = new Snack("치즈볼", 3000);
        snacks[9] = new Snack("쿵야 양파 사워 크림 새우스낵", 5000);

        return snacks;
    }
}
