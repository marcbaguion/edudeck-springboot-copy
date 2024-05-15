package com.it332.edudeck.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it332.edudeck.Entity.DocumentToFlashcardAIEntity;
import com.it332.edudeck.Entity.FlashcardDeckEntity;
import com.it332.edudeck.Entity.UserEntity;
import com.it332.edudeck.Repository.FlashcardDeckRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FlashcardDeckService {

    @Autowired
    private FlashcardDeckRepository flashcardDeckRepository;

    public FlashcardDeckEntity createFlashcardDeck(String title, UserEntity user, DocumentToFlashcardAIEntity documentToFlashcardAI) {
        FlashcardDeckEntity flashcardDeck = new FlashcardDeckEntity(title, user, documentToFlashcardAI);
        return flashcardDeckRepository.save(flashcardDeck);
    }

    public List<FlashcardDeckEntity> getAllFlashcardDecks() {
        return flashcardDeckRepository.findAll();
    }

    public Optional<FlashcardDeckEntity> getFlashcardDeckById(int id) {
        return flashcardDeckRepository.findById(id);
    }

    public void deleteFlashcardDeck(int id) {
        Optional<FlashcardDeckEntity> flashcardDeck = flashcardDeckRepository.findById(id);
        if (flashcardDeck.isPresent()) {
            FlashcardDeckEntity deck = flashcardDeck.get();
            deck.setDeleted(true);
            flashcardDeckRepository.save(deck);
        }
    }
}