package com.it332.edudeck.Service;

import com.it332.edudeck.Entity.Payment;
import com.it332.edudeck.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public Payment processPayment(Payment payment) {
        // Additional logic for payment processing can be added here
        return paymentRepository.save(payment);
    }
}
