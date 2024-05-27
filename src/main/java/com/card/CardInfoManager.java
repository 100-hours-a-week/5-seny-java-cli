package com.card;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public class CardInfoManager {
    private static final String CARD_FILE_PATH = "assets/cards.json";
    private List<Card> cards; // 카드 정보를 저장할 리스트
    private Gson gson; // JSON 변환을 위한 Gson 객체

    public CardInfoManager(){
        gson = new Gson(); // Gson 객체 초기화
        loadCards(); // 카드 정보 로드
    }

    // 카드 정보를 로드하는 메서드
    private synchronized void loadCards() {
        try (Reader reader = new FileReader(CARD_FILE_PATH)) { // 파일을 읽기 위한 리더 객체 생성
            Type cardListType = new TypeToken<List<Card>>(){}.getType(); // JSON을 List<Card>로 변환하기 위한 타입 정보 지정
            cards = gson.fromJson(reader, cardListType); // JSON 파일을 읽어서 cards 리스트에 저장
        } catch (IOException e) {
            e.printStackTrace();
            cards = null; // 에러 발생 시 cards 리스트를 null로 초기화
        }
    }

    // 카드 정보를 저장하는 메서드
    private synchronized void saveCards() {
        try (Writer writer = new FileWriter(CARD_FILE_PATH)) { // 파일을 쓰기 위한 라이터 객체 생성
            gson.toJson(cards, writer); // cards 리스트를 JSON 문자열로 변환하여 파일에 저장
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 카드 정보를 업데이트하는 메서드
    public synchronized boolean updateCardBalance(String cardNumber, int amount) {
        for (Card card : cards) { // cards 리스트를 순회하며
            if (card.getCardNumber().equals(cardNumber)) { // 카드 번호가 일치하는 카드 정보를 찾으면
                if (card.getBalance() >= amount) { // 잔액이 충분한지 확인
                    card.setBalance(card.getBalance() - amount); // 카드 잔액을 갱신
                    saveCards(); // 카드 정보를 저장
                    return true; // 업데이트 성공 시 true 반환
                }
            }
        }
            System.out.println("잔액이 부족합니다."); // 입력한 카드번호와 일치하는 카드 정보가 없을 경우 메시지 출력
            return false; // 업데이트 실패 시 false 반환
    }

    // 카드 정보를 가져오는 메서드
    public Card getCardInfo(String cardNumber) {
        for (Card card : cards) { // cards 리스트를 순회하며
            if (card.getCardNumber().equals(cardNumber)) { // 카드 번호가 일치하는 카드 정보를 찾으면
                return card; // 해당 카드 정보 반환
            }
        }
        return null; // 일치하는 카드 정보가 없을 경우 null 반환
    }

    // 내부 클래스 : 카드 정보 구조체
    public class Card {
        private String cardNumber; // 카드 번호
        private String cardType; // 카드 종류
        private int balance; // 카드 잔액

        public String getCardNumber() {
            return cardNumber;
        }

        public String getCardType() {
            return cardType;
        }

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }
    }
}

