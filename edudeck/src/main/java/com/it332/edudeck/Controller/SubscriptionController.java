package com.it332.edudeck.Controller;

import com.it332.edudeck.Entity.SubscriptionEntity;
import com.it332.edudeck.Service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subscription")
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping
    public ResponseEntity<SubscriptionEntity> createSubscription(@RequestBody SubscriptionEntity subscriptionEntity) {
        SubscriptionEntity createdSubscriptionEntity = subscriptionService.createSubscription(subscriptionEntity);
        return new ResponseEntity<>(createdSubscriptionEntity, HttpStatus.CREATED);
    }
}
