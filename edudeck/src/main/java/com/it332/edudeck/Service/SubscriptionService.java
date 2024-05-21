package com.it332.edudeck.Service;

import com.it332.edudeck.Entity.SubscriptionEntity;
import com.it332.edudeck.Repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SubscriptionService {
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public SubscriptionEntity createSubscription(SubscriptionEntity sub) {
        // Logic for handling free trial
        if ("Annual".equals(sub.getType()) && sub.getFreeTrial() == null) {
            sub.setFreeTrial(true);
            sub.setStartDate(LocalDate.now());
            sub.setEndDate(LocalDate.now().plusDays(7));
        }
        return subscriptionRepository.save(sub);
    }
}
