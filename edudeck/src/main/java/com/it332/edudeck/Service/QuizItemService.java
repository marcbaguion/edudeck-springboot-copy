package com.it332.edudeck.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it332.edudeck.Entity.QuizItemEntity;
import com.it332.edudeck.Repository.QuizItemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuizItemService {

    @Autowired
    private QuizItemRepository quizItemRepository;

    public QuizItemEntity createQuizItem(QuizItemEntity quizItem) {
        return quizItemRepository.save(quizItem);
    }

    public List<QuizItemEntity> getAllQuizItems() {
        return quizItemRepository.findAll();
    }

    public Optional<QuizItemEntity> getQuizItemById(int id) {
        return quizItemRepository.findById(id);
    }

    public boolean softDeleteQuizItem(int id) {
        Optional<QuizItemEntity> quizItemOptional = quizItemRepository.findById(id);
        if (quizItemOptional.isPresent()) {
            QuizItemEntity quizItem = quizItemOptional.get();
            quizItem.setDeleted(true);
            quizItemRepository.save(quizItem);
            return true;
        } else {
            return false;
        }
    }
}

