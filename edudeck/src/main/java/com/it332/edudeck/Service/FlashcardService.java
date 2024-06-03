package com.it332.edudeck.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it332.edudeck.ResourceNotFoundException;
import com.it332.edudeck.Entity.FlashcardDeckEntity;
import com.it332.edudeck.Entity.FlashcardEntity;
import com.it332.edudeck.Repository.FlashcardRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FlashcardService {

    @Autowired
    private FlashcardRepository flashcardRepository;

    public FlashcardEntity createFlashcard(String question, String answer, FlashcardDeckEntity flashcardDeck) {
        FlashcardEntity flashcard = new FlashcardEntity(question, answer, flashcardDeck);
        return flashcardRepository.save(flashcard);
    }

    public FlashcardEntity updateFlashcard(int id, String question, String answer) {
        FlashcardEntity flashcard = flashcardRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Flashcard not found"));
        flashcard.setQuestion(question);
        flashcard.setAnswer(answer);
        return flashcardRepository.save(flashcard);
    }
     
    public List<FlashcardEntity> getAllFlashcards() {
        return flashcardRepository.findAll();
    }

    public List<FlashcardEntity> getAllFlashcardsByDeckId(int deckId) {
        return flashcardRepository.findByFlashcardDeck_deckId(deckId);
    }

    public Optional<FlashcardEntity> getFlashcardById(int id) {
        return flashcardRepository.findById(id);
    }

    public void deleteFlashcard(int id) {
        Optional<FlashcardEntity> flashcard = flashcardRepository.findById(id);
        if (flashcard.isPresent()) {
            FlashcardEntity card = flashcard.get();
            card.setDeleted(true);
            flashcardRepository.save(card);
        }
    }
    
}
