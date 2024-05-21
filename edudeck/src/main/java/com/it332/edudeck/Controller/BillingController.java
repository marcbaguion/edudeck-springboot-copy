package com.it332.edudeck.Controller;


import com.it332.edudeck.Entity.BillingEntity;
import com.it332.edudeck.Service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/billing")
public class BillingController {
    @Autowired
    private BillingService billingService;

    @PostMapping
    public ResponseEntity<BillingEntity> processBilling(@RequestBody BillingEntity billingEntity) {
        BillingEntity processedBillingEntity = billingService.processBilling(billingEntity);
        return new ResponseEntity<>(processedBillingEntity, HttpStatus.CREATED);
    }
}
