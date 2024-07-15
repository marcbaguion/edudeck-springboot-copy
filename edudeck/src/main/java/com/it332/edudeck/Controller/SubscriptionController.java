package com.it332.edudeck.Controller;

import com.it332.edudeck.Entity.Subscription;
import com.it332.edudeck.Service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sub")
public class SubscriptionController {
    @Autowired
    SubscriptionService subserv;

    //R -Read
    @GetMapping("/getAllSubscription")
    public List<Subscription> getAllSubscription(){
        return subserv.getAllSubscription();
    }

    //P - Post
    @PostMapping
    public ResponseEntity<Subscription> createSubscription(@RequestBody Subscription subscription) {
        Subscription createdSubscription = subserv.createSubscription(subscription);
        return new ResponseEntity<>(createdSubscription, HttpStatus.CREATED);
    }
}
