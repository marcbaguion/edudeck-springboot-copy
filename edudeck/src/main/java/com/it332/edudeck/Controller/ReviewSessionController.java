package com.it332.edudeck.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.it332.edudeck.Entity.FlashcardDeck;
import com.it332.edudeck.Entity.ReviewSession;
import com.it332.edudeck.Entity.User;
import com.it332.edudeck.Service.ReviewSessionService;
import com.it332.edudeck.Repository.FlashcardDeckRepository;
import com.it332.edudeck.Repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/ReviewSession")
public class ReviewSessionController {

    @Autowired
    private ReviewSessionService reviewSessionService;

    @Autowired
    private FlashcardDeckRepository flashcardDeckRepository;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/getAllReviewSession")
    public List<ReviewSession> getAllReviewSessions() {
        return reviewSessionService.getAllReviewSessions();
    }

    @GetMapping("/getReviewSession/{id}")
    public ReviewSession getReviewSessionById(@PathVariable int id) {
        return reviewSessionService.getReviewSessionById(id);
    }

    @PostMapping("/create")
public ReviewSession createReviewSession(@RequestBody ReviewSession reviewSession) {
    try {
        int userId = reviewSession.getUser().getUserid();
        int deckId = reviewSession.getFlashcardDeck().getDeckId();

        // Log incoming request data for debugging
        System.out.println("Received ReviewSession creation request: userId=" + userId + ", deckId=" + deckId);

        // Fetch User and Deck from the repository
        User user = userRepository.findById(userId)
                        .orElseThrow(() -> new RuntimeException("User not found for ID: " + userId));
        FlashcardDeck deck = flashcardDeckRepository.findById(deckId)
                        .orElseThrow(() -> new RuntimeException("Deck not found for ID: " + deckId));

        // Set the actual User and Deck objects to the ReviewSession
        reviewSession.setUser(user);
        reviewSession.setFlashcardDeck(deck);
        reviewSession.setStartTime(LocalDateTime.now());

        // Log success before saving
        System.out.println("Creating Review Session for User ID: " + userId + " with Deck ID: " + deckId);

        // Save the ReviewSession
        return reviewSessionService.createReviewSession(reviewSession);
    } catch (Exception e) {
        // Log the error details for better debugging
        e.printStackTrace();
        throw new RuntimeException("Failed to create review session: " + e.getMessage());
    }
}

    @PutMapping("/updateReviewSession/{id}")
    public ReviewSession updateReviewSession(@PathVariable int id, @RequestBody ReviewSession reviewSessionDetails) {
        return reviewSessionService.updateReviewSession(id, reviewSessionDetails);
    }

    @DeleteMapping("/deleteReviewSession/{id}")
    public void deleteReviewSession(@PathVariable int id) {
        reviewSessionService.deleteReviewSession(id);
    }

    @PutMapping("/markMemorized")
    public void markMemorized(@RequestParam int reviewSessionId, @RequestParam boolean isMemorized) {
        reviewSessionService.updateMemorizedStatus(reviewSessionId, isMemorized);
    }

    @PutMapping("/updateCurrentIndex")
    public void updateCurrentIndex(@RequestParam int reviewSessionId, @RequestParam int currentCardIndex, @RequestParam boolean isMemorized) {
        reviewSessionService.updateCurrentIndex(reviewSessionId, currentCardIndex, isMemorized);
    }

    @GetMapping("/checkSessionStatus")
public ResponseEntity<String> checkSessionStatus(@RequestParam int reviewSessionId) {
    // Implement your logic here to check if the session has ended
    // You could return some status or a boolean value indicating the session status.
    ReviewSession reviewSession = reviewSessionService.getReviewSessionById(reviewSessionId);
    if (reviewSession != null && reviewSession.getEndTime() != null && LocalDateTime.now().isAfter(reviewSession.getEndTime())) {
        return ResponseEntity.ok("Session Ended");
    } else {
        return ResponseEntity.ok("Session Ongoing");
    }
}

@PutMapping("/endSession")
public ResponseEntity<String> endSession(
    @RequestParam int reviewSessionId,
    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
    ReviewSession reviewSession = reviewSessionService.getReviewSessionById(reviewSessionId);
    if (reviewSession != null) {
        reviewSession.setEndTime(endTime);
        reviewSessionService.updateReviewSession(reviewSession);
        return ResponseEntity.ok("Session ended successfully");
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Session not found");
    }
}


}