package com.it332.edudeck.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it332.edudeck.Entity.FlashcardDeckEntity;
import com.it332.edudeck.Entity.UserEntity;
import com.it332.edudeck.Repository.FlashcardDeckRepository;
import com.it332.edudeck.Repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlashcardDeckService {

    @Autowired
    private FlashcardDeckRepository flashcardDeckRepository;

    @Autowired
    private UserRepository userRepository;

    public FlashcardDeckEntity createFlashcardDeck(String title, UserEntity user) {
        FlashcardDeckEntity flashcardDeck = new FlashcardDeckEntity(title, user);
        return flashcardDeckRepository.save(flashcardDeck);
    }

    public List<FlashcardDeckEntity> getAllFlashcardDecks() {
        return flashcardDeckRepository.findAll().stream()
	        .filter(deck -> !deck.isDeleted())
	        .collect(Collectors.toList());
    }

    public List<FlashcardDeckEntity> getDecksByUser(int userId) {
        UserEntity user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }
        return flashcardDeckRepository.findByUserAndIsDeletedFalse(user);
    }

    public Optional<FlashcardDeckEntity> getFlashcardDeckById(int id) {
        return flashcardDeckRepository.findById(id);
    }

    // Update
	@SuppressWarnings("finally")
	public FlashcardDeckEntity updateDeck(int deckId, FlashcardDeckEntity newDeckDetails) {
		FlashcardDeckEntity deck = new FlashcardDeckEntity();
		try {
			deck = flashcardDeckRepository.findById(deckId).get();
			//deck = flashcardDeckRepository.findById(deckId).orElseThrow(() -> new NoSuchElementException("Flashcard Deck " + deckId + " does not exist!"));

			deck.setTitle(newDeckDetails.getTitle());
		}catch(NoSuchElementException ex){
			throw new NoSuchElementException("Flashcard Deck " + deckId + " does not exist!");
		}finally {
			return flashcardDeckRepository.save(deck);
		}
	}

    public String deleteFlashcardDeck(int deckId) {
        String msg = "";
        Optional<FlashcardDeckEntity> flashcardDeck = flashcardDeckRepository.findById(deckId);
        if (flashcardDeck.isPresent()) {
            FlashcardDeckEntity deck = flashcardDeck.get();
            deck.setDeleted(true);
            flashcardDeckRepository.save(deck);
            msg = "Flashcard Deck " + deckId + " is successfully deleted!";
	    } else {
	        msg = "Flashcard Deck " + deckId + " does not exist.";
	    }
	    return msg;
    }
    
}