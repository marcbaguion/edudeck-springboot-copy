package com.it332.edudeck.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it332.edudeck.Entity.QuizEntity;
import com.it332.edudeck.Repository.QuizRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    public QuizEntity createQuiz(QuizEntity quiz) {
        return quizRepository.save(quiz);
    }

    public List<QuizEntity> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public Optional<QuizEntity> getQuizById(int id) {
        return quizRepository.findById(id);
    }

    public boolean softDeleteQuiz(int id) {
        Optional<QuizEntity> quizOptional = quizRepository.findById(id);
        if (quizOptional.isPresent()) {
            QuizEntity quiz = quizOptional.get();
            quiz.setDeleted(true);
            quizRepository.save(quiz);
            return true;
        } else {
            return false;
        }
    }
}
