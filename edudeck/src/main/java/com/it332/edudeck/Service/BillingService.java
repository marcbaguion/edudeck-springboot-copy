package com.it332.edudeck.Service;

import com.it332.edudeck.Entity.BillingEntity;
import com.it332.edudeck.Repository.BillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillingService {
    @Autowired
    private BillingRepository billingRepository;

    public BillingEntity processBilling(BillingEntity billingEntity) {
        // Logic to handle billing summary and final steps
        return billingRepository.save(billingEntity);
    }
}
