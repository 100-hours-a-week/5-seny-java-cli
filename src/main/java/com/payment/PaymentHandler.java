package com.payment;

public interface PaymentHandler {
    void processPayment(int amount) throws InterruptedException;
}
