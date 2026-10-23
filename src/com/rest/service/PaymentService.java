package com.rest.service;

import com.rest.models.Payment;
import com.rest.payment.PaymentMethod;
import com.rest.repository.PaymentRepository;
import java.util.List;

public class PaymentService {

    private final PaymentRepository repository;
    private int nextPaymentId = 1;

    public PaymentService() {
        repository = new PaymentRepository();
    }

    public Payment processPayment(
            PaymentMethod method, double amount) {

        // Runtime polymorphism
        method.pay(amount);

        Payment payment = new Payment(
                nextPaymentId++,
                amount,
                method.getMethodName(),
                "SUCCESS");

        repository.save(payment);

        return payment;
    }

    public List<Payment> getAllPayments() {
        return repository.findAll();
    }

    public Payment getPaymentById(int id) {
        Payment payment = repository.findById(id);

        return payment;
    }
}