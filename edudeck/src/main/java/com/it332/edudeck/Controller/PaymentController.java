package com.it332.edudeck.Controller;

import com.it332.edudeck.Entity.PaymentEntity;
import com.it332.edudeck.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentEntity> processPayment(@RequestBody PaymentEntity paymentEntity) {
        PaymentEntity processedPaymentEntity = paymentService.processPayment(paymentEntity);
        return new ResponseEntity<>(processedPaymentEntity, HttpStatus.CREATED);
    }
}
