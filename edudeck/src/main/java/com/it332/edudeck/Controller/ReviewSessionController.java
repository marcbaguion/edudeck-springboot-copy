package com.it332.edudeck.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.it332.edudeck.Entity.ReviewSessionEntity;
import com.it332.edudeck.Service.ReviewSessionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/flashcardreviewsessions")
public class ReviewSessionController {

    @Autowired
    private ReviewSessionService flashcardReviewSessionService;

    @PostMapping("/createReviewSession")
    public ResponseEntity<ReviewSessionEntity> createReviewSession(@RequestBody ReviewSessionEntity flashcardReviewSession) {
        ReviewSessionEntity createdSession = flashcardReviewSessionService.createReviewSession(flashcardReviewSession);
        return ResponseEntity.ok(createdSession);
    }

    @GetMapping("/getAllReviewSessions")
    public ResponseEntity<List<ReviewSessionEntity>> getAllReviewSessions() {
        return ResponseEntity.ok(flashcardReviewSessionService.getAllReviewSessions());
    }

    @GetMapping("/getReviewSessionById/{id}")
    public ResponseEntity<ReviewSessionEntity> getReviewSessionById(@PathVariable int id) {
        Optional<ReviewSessionEntity> flashcardReviewSession = flashcardReviewSessionService.getReviewSessionById(id);
        return flashcardReviewSession.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/deleteReviewSession/{id}")
    public ResponseEntity<Void> deleteReviewSession(@PathVariable int id) {
        flashcardReviewSessionService.deleteReviewSession(id);
        return ResponseEntity.noContent().build();
    }
}

