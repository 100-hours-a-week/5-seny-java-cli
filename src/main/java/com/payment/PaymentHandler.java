package com.payment;

public interface PaymentHandler {
    boolean processPayment(int amount) throws InterruptedException;
}
