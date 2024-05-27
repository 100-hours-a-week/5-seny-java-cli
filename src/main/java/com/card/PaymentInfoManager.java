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

    public synchronized boolean updatePaymentStatus(String paymentId, boolean status) {
        for (Payment payment : payments) {
            if (payment.getPaymentId() == Integer.parseInt(paymentId)) {
                payment.setStatus(status);
                savePayments();
                return true;
            }
        }
        System.out.println("해당 ID의 결제 정보가 없습니다.");
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

    public synchronized void addPayment(int amount) {
        // 결제 ID는 1부터 시작하며, 가장 마지막 결제 ID에 1을 더한 값으로 결정
        int newPaymentId = payments.isEmpty() ? 1 : payments.get(payments.size() - 1).getPaymentId() + 1;
        Payment newPayment = new Payment(newPaymentId, amount);
        payments.add(newPayment);
        savePayments();
    }

    public class Payment {
        private int paymentId;
        private int amount;
        private boolean status;

        public Payment(int paymentId, int amount) {
            this.paymentId = paymentId;
            this.amount = amount;
            this.status = false;
        }

        public int getPaymentId() {
            return paymentId;
        }

        public int getAmount() {
            return amount;
        }

        public boolean getStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }
    }

}
