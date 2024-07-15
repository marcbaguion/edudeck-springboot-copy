package com.it332.edudeck.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it332.edudeck.Entity.ReviewSession;
import com.it332.edudeck.Repository.ReviewSessionRepository;

import java.util.List;

@Service
public class ReviewSessionService {

    @Autowired
    private ReviewSessionRepository reviewSessionRepository;

    public List<ReviewSession> getAllReviewSessions() {
        return reviewSessionRepository.findAll();
    }

    public ReviewSession getReviewSessionById(int id) {
        return reviewSessionRepository.findById(id).orElse(null);
    }

    public ReviewSession createReviewSession(ReviewSession reviewSession) {
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
}

