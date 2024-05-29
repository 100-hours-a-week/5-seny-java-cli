package com.card;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public class PaymentInfoManager {
    private static final String PAYMENT_FILE_PATH = "assets/payments.json";
    private List<Payment> payments;
    private Gson gson;

    public PaymentInfoManager(){
        gson = new Gson();
        loadPayments();
    }

    private synchronized void loadPayments() {
        try (Reader reader = new FileReader(PAYMENT_FILE_PATH)) {
            Type paymentListType = new TypeToken<List<Payment>>(){}.getType();
            payments = gson.fromJson(reader, paymentListType);
        } catch (IOException e) {
            e.printStackTrace();
            payments = null;
        }
    }

    private synchronized void savePayments() {
        try (Writer writer = new FileWriter(PAYMENT_FILE_PATH)) {
            gson.toJson(payments, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized boolean updatePaymentStatus(int paymentId, int status) {
        for (Payment payment : payments) {
            System.out.println("바꾸려는payment상태 : " + payment.getStatus() + "바꾸려는status : " + status);
            if (payment.getPaymentId() == paymentId) {
                payment.setStatus(status); // 0:결제 전 초기상태 OR 잔액부족, 1 : 결제 완료, 2 : 점검 중, 3 : 시간 초과
                savePayments();
                System.out.println("바뀐payment상태 : " + payment.getStatus());
                return true;
            }
        }
        return false;
    }

    public Payment getPaymentInfo(int paymentId) {
        for (Payment payment : payments) {
            if (payment.getPaymentId() == paymentId) {
                return payment;
            }
        }
        return null;
    }

    public synchronized int addPayment(int amount) {
        // 결제 ID는 1부터 시작하며, 가장 마지막 결제 ID에 1을 더한 값으로 결정
        int newPaymentId = payments.isEmpty() ? 1 : payments.get(payments.size() - 1).getPaymentId() + 1;
        Payment newPayment = new Payment(newPaymentId, amount);
        payments.add(newPayment);
        savePayments();
        return newPaymentId;
    }

    public class Payment {
        private int paymentId;
        private int amount;
        private int status;

        public Payment(int paymentId, int amount) {
            this.paymentId = paymentId;
            this.amount = amount;
            this.status = 0;
        }

        public int getPaymentId() {
            return paymentId;
        }

        public int getAmount() {
            return amount;
        }

        public int getStatus() {
            return status; // 0: 초기상태(결제시도전), 1 : 결제 완료, 2 : 점검 중, 3 : 시간 초과
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }

}
