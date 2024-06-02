package com.it332.edudeck.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.it332.edudeck.Entity.ReviewSessionEntity;
import com.it332.edudeck.Service.ReviewSessionService;

import java.util.List;

@RestController
@RequestMapping("/api/ReviewSession")
public class ReviewSessionController {

    @Autowired
    private ReviewSessionService reviewSessionService;

    @GetMapping("/getAllReviewSession")
    public List<ReviewSessionEntity> getAllReviewSessions() {
        return reviewSessionService.getAllReviewSessions();
    }

    @GetMapping("/getReviewSession/{id}")
    public ReviewSessionEntity getReviewSessionById(@PathVariable int id) {
        return reviewSessionService.getReviewSessionById(id);
    }

    @PostMapping("/createReviewSession")
    public ReviewSessionEntity createReviewSession(@RequestBody ReviewSessionEntity reviewSession) {
        return reviewSessionService.createReviewSession(reviewSession);
    }

    @PutMapping("/updateReviewSession/{id}")
    public ReviewSessionEntity updateReviewSession(@PathVariable int id, @RequestBody ReviewSessionEntity reviewSessionDetails) {
        return reviewSessionService.updateReviewSession(id, reviewSessionDetails);
    }

    @DeleteMapping("/deleteReviewSession/{id}")
    public void deleteReviewSession(@PathVariable int id) {
        reviewSessionService.deleteReviewSession(id);
    }
}