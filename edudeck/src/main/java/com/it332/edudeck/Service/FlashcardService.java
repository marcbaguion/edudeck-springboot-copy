package com.it332.edudeck.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it332.edudeck.ResourceNotFoundException;
import com.it332.edudeck.Entity.FlashcardDeck;
import com.it332.edudeck.Entity.Flashcard;
import com.it332.edudeck.Repository.FlashcardRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FlashcardService {

    @Autowired
    private FlashcardRepository flashcardRepository;

    public Flashcard createFlashcard(String question, String answer, FlashcardDeck flashcardDeck) {
        Flashcard flashcard = new Flashcard(question, answer, flashcardDeck);
        return flashcardRepository.save(flashcard);
    }

    public Flashcard updateFlashcard(int id, String question, String answer) {
        Flashcard flashcard = flashcardRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Flashcard not found"));
        flashcard.setQuestion(question);
        flashcard.setAnswer(answer);
        return flashcardRepository.save(flashcard);
    }
     
    public List<Flashcard> getAllFlashcards() {
        return flashcardRepository.findAll();
    }

    public List<Flashcard> getAllFlashcardsByDeckId(int deckId) {
        return flashcardRepository.findByFlashcardDeck_deckId(deckId);
    }

    public Optional<Flashcard> getFlashcardById(int id) {
        return flashcardRepository.findById(id);
    }

    public void deleteFlashcard(int id) {
        Optional<Flashcard> flashcard = flashcardRepository.findById(id);
        if (flashcard.isPresent()) {
            Flashcard card = flashcard.get();
            card.setDeleted(true);
            flashcardRepository.save(card);
        }
    }
    
}
