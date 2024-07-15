package com.it332.edudeck.Service;

import com.it332.edudeck.Entity.Subscription;
import com.it332.edudeck.Repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SubscriptionService {
    @Autowired
    SubscriptionRepository subrepo;

    public Subscription createSubscription(Subscription sub) {
        // Logic for handling free trial
        if ("Annual".equals(sub.getType()) && sub.getFreeTrial() == null) {
            sub.setFreeTrial(true);
            sub.setStartDate(LocalDate.now());
            sub.setEndDate(LocalDate.now().plusDays(7));
        }
        return subrepo.save(sub);
    }


    public List<Subscription> getAllSubscription(){
        return subrepo.findAll();
    }
}
