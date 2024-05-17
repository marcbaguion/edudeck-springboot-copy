package com.it332.edudeck.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it332.edudeck.Entity.QuizSessionEntity;
import com.it332.edudeck.Repository.QuizSessionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuizSessionService {

    @Autowired
    private QuizSessionRepository quizSessionRepository;

    public QuizSessionEntity createQuizSession(QuizSessionEntity quizSession) {
        return quizSessionRepository.save(quizSession);
    }

    public List<QuizSessionEntity> getAllQuizSessions() {
        return quizSessionRepository.findAll();
    }

    public Optional<QuizSessionEntity> getQuizSessionById(int id) {
        return quizSessionRepository.findById(id);
    }

    public boolean softDeleteQuizSession(int id) {
        Optional<QuizSessionEntity> quizSessionOptional = quizSessionRepository.findById(id);
        if (quizSessionOptional.isPresent()) {
            QuizSessionEntity quizSession = quizSessionOptional.get();
            quizSession.setDeleted(true);
            quizSessionRepository.save(quizSession);
            return true;
        } else {
            return false;
        }
    }
}
