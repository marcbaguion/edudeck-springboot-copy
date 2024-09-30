package com.it332.edudeck.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it332.edudeck.Entity.Flashcard;
import com.it332.edudeck.Entity.ReviewSession;
import com.it332.edudeck.Repository.FlashcardDeckRepository;
import com.it332.edudeck.Repository.ReviewSessionRepository;
import com.it332.edudeck.Repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewSessionService {

    @Autowired
    private ReviewSessionRepository reviewSessionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FlashcardDeckRepository flashcardDeckRepository;

    public List<ReviewSession> getAllReviewSessions() {
        return reviewSessionRepository.findAll();
    }

    public ReviewSession getReviewSessionById(int id) {
        return reviewSessionRepository.findById(id).orElse(null);
    }

    public ReviewSession createReviewSession(int userId, int deckId) {
        ReviewSession reviewSession = new ReviewSession();
        reviewSession.setStartTime(LocalDateTime.now());
        reviewSession.setUser(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found")));
        reviewSession.setFlashcardDeck(flashcardDeckRepository.findById(deckId).orElseThrow(() -> new RuntimeException("Deck not found")));

        return reviewSessionRepository.save(reviewSession);
    }

    public ReviewSession updateReviewSession(int id, ReviewSession reviewSessionDetails) {
        ReviewSession reviewSession = reviewSessionRepository.findById(id).orElse(null);
        if (reviewSession != null) {
            reviewSession.setStartTime(reviewSessionDetails.getStartTime());
            reviewSession.setEndTime(reviewSessionDetails.getEndTime());
            reviewSession.setUser(reviewSessionDetails.getUser());
            reviewSession.setFlashcardDeck(reviewSessionDetails.getFlashcardDeck());
            reviewSession.setCurrentFlashcard(reviewSessionDetails.getCurrentFlashcard());
            return reviewSessionRepository.save(reviewSession);
        }
        return null;
    }

    public void deleteReviewSession(int id) {
        reviewSessionRepository.deleteById(id);
    }

    public void updateMemorizedStatus(int reviewSessionId, boolean isMemorized) {
        ReviewSession reviewSession = reviewSessionRepository.findById(reviewSessionId).orElseThrow();
        reviewSession.setIsMemorized(isMemorized);
        reviewSessionRepository.save(reviewSession);
    }
    
    public void updateCurrentIndex(int reviewSessionId, int currentCardIndex, boolean isMemorized) {
        ReviewSession reviewSession = reviewSessionRepository.findById(reviewSessionId).orElseThrow();
        reviewSession.setCurrentCardIndex(currentCardIndex);
        reviewSession.setIsMemorized(isMemorized);
        reviewSessionRepository.save(reviewSession);
    }

    public ReviewSession createReviewSession(ReviewSession reviewSession) {
        return reviewSessionRepository.save(reviewSession);  // Correct implementation for saving the session
    }

    public ReviewSession updateReviewSession(ReviewSession reviewSession) {
        try {
            return reviewSessionRepository.save(reviewSession);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update review session: " + e.getMessage());
        }
    }
       
}