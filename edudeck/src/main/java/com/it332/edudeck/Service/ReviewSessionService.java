package com.it332.edudeck.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it332.edudeck.Entity.ReviewSessionEntity;
import com.it332.edudeck.Repository.ReviewSessionRepository;

import java.util.List;

@Service
public class ReviewSessionService {

    @Autowired
    private ReviewSessionRepository reviewSessionRepository;

    public List<ReviewSessionEntity> getAllReviewSessions() {
        return reviewSessionRepository.findAll();
    }

    public ReviewSessionEntity getReviewSessionById(int id) {
        return reviewSessionRepository.findById(id).orElse(null);
    }

    public ReviewSessionEntity createReviewSession(ReviewSessionEntity reviewSession) {
        return reviewSessionRepository.save(reviewSession);
    }

    public ReviewSessionEntity updateReviewSession(int id, ReviewSessionEntity reviewSessionDetails) {
        ReviewSessionEntity reviewSession = reviewSessionRepository.findById(id).orElse(null);
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

