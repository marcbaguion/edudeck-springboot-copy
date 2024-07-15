package com.it332.edudeck.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it332.edudeck.Entity.QuizSession;
import com.it332.edudeck.Repository.QuizSessionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuizSessionService {

    @Autowired
    private QuizSessionRepository quizSessionRepository;

    public QuizSession createQuizSession(QuizSession quizSession) {
        return quizSessionRepository.save(quizSession);
    }

    public List<QuizSession> getAllQuizSessions() {
        return quizSessionRepository.findAll();
    }

    public Optional<QuizSession> getQuizSessionById(int id) {
        return quizSessionRepository.findById(id);
    }

    public boolean softDeleteQuizSession(int id) {
        Optional<QuizSession> quizSessionOptional = quizSessionRepository.findById(id);
        if (quizSessionOptional.isPresent()) {
            QuizSession quizSession = quizSessionOptional.get();
            quizSession.setDeleted(true);
            quizSessionRepository.save(quizSession);
            return true;
        } else {
            return false;
        }
    }
}
