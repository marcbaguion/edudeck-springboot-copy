package com.it332.edudeck.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it332.edudeck.Entity.FlashcardAnalyticsEntity;
import com.it332.edudeck.Repository.FlashcardAnalyticsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FlashcardAnalyticsService {

    @Autowired
    private FlashcardAnalyticsRepository flashcardAnalyticsRepository;

    public FlashcardAnalyticsEntity createFlashcardAnalytics(FlashcardAnalyticsEntity flashcardAnalytics) {
        return flashcardAnalyticsRepository.save(flashcardAnalytics);
    }

    public List<FlashcardAnalyticsEntity> getAllFlashcardAnalytics() {
        return flashcardAnalyticsRepository.findAll();
    }

    public Optional<FlashcardAnalyticsEntity> getFlashcardAnalyticsById(int id) {
        return flashcardAnalyticsRepository.findById(id);
    }

    public boolean softDeleteFlashcardAnalytics(int id) {
        Optional<FlashcardAnalyticsEntity> flashcardAnalyticsOptional = flashcardAnalyticsRepository.findById(id);
        if (flashcardAnalyticsOptional.isPresent()) {
            FlashcardAnalyticsEntity flashcardAnalytics = flashcardAnalyticsOptional.get();
            flashcardAnalytics.setDeleted(true);
            flashcardAnalyticsRepository.save(flashcardAnalytics);
            return true;
        } else {
            return false;
        }
    }
}
