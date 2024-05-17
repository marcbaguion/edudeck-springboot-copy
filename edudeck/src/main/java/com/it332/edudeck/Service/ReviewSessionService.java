package com.it332.edudeck.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it332.edudeck.Entity.ReviewSessionEntity;
import com.it332.edudeck.Repository.ReviewSessionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewSessionService {

    @Autowired
    private ReviewSessionRepository flashcardReviewSessionRepository;

    public ReviewSessionEntity createReviewSession(ReviewSessionEntity flashcardReviewSession) {
        return flashcardReviewSessionRepository.save(flashcardReviewSession);
    }

    public List<ReviewSessionEntity> getAllReviewSessions() {
        return flashcardReviewSessionRepository.findAll();
    }

    public Optional<ReviewSessionEntity> getReviewSessionById(int id) {
        return flashcardReviewSessionRepository.findById(id);
    }

    public void deleteReviewSession(int id) {
        flashcardReviewSessionRepository.deleteById(id);
    }
}

