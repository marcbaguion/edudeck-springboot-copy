package com.it332.edudeck.Service;

import com.it332.edudeck.Entity.Billing;
import com.it332.edudeck.Repository.BillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillingService {
    @Autowired
    private BillingRepository billingRepository;

    public Billing processBilling(Billing billing) {
        // Logic to handle billing summary and final steps
        return billingRepository.save(billing);
    }
}
