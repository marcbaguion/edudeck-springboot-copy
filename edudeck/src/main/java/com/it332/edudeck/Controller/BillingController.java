package com.it332.edudeck.Controller;


import com.it332.edudeck.Entity.Billing;
import com.it332.edudeck.Service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/billing/{id}")
public class BillingController {
    @Autowired
    private BillingService billingService;

    @PostMapping
    public ResponseEntity<Billing> processBilling(@RequestBody Billing billing) {
        Billing processedBilling = billingService.processBilling(billing);
        return new ResponseEntity<>(processedBilling, HttpStatus.CREATED);
    }
}
