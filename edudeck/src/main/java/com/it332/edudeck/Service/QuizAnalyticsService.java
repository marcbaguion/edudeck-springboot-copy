package com.it332.edudeck.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it332.edudeck.Entity.QuizAnalyticsEntity;
import com.it332.edudeck.Repository.QuizAnalyticsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuizAnalyticsService {

    @Autowired
    private QuizAnalyticsRepository quizAnalyticsRepository;

    public QuizAnalyticsEntity createQuizAnalytics(QuizAnalyticsEntity quizAnalytics) {
        return quizAnalyticsRepository.save(quizAnalytics);
    }

    public List<QuizAnalyticsEntity> getAllQuizAnalytics() {
        return quizAnalyticsRepository.findAll();
    }

    public Optional<QuizAnalyticsEntity> getQuizAnalyticsById(int id) {
        return quizAnalyticsRepository.findById(id);
    }

    public boolean softDeleteQuizAnalytics(int id) {
        Optional<QuizAnalyticsEntity> quizAnalyticsOptional = quizAnalyticsRepository.findById(id);
        if (quizAnalyticsOptional.isPresent()) {
            QuizAnalyticsEntity quizAnalytics = quizAnalyticsOptional.get();
            quizAnalytics.setDeleted(true);
            quizAnalyticsRepository.save(quizAnalytics);
            return true;
        } else {
            return false;
        }
    }
}
