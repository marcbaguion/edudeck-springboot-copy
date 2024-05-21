package com.it332.edudeck.Service;

import com.it332.edudeck.Entity.PaymentEntity;
import com.it332.edudeck.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public PaymentEntity processPayment(PaymentEntity paymentEntity) {
        // Additional logic for payment processing can be added here
        return paymentRepository.save(paymentEntity);
    }
}
