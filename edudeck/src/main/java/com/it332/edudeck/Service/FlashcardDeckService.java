package com.it332.edudeck.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it332.edudeck.Entity.FlashcardDeck;
import com.it332.edudeck.Entity.User;
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

    public FlashcardDeck createFlashcardDeck(String title, User user) {
        FlashcardDeck flashcardDeck = new FlashcardDeck(title, user);
        return flashcardDeckRepository.save(flashcardDeck);
    }

    public List<FlashcardDeck> getAllFlashcardDecks() {
        return flashcardDeckRepository.findAll().stream()
	        .filter(deck -> !deck.isDeleted())
	        .collect(Collectors.toList());
    }

    public List<FlashcardDeck> getDecksByUser(int userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return null;
        }
        return flashcardDeckRepository.findByUserAndIsDeletedFalse(user);
    }

    public Optional<FlashcardDeck> getFlashcardDeckById(int id) {
        return flashcardDeckRepository.findById(id);
    }

    // Update
	@SuppressWarnings("finally")
	public FlashcardDeck updateDeck(int deckId, FlashcardDeck newDeckDetails) {
		FlashcardDeck deck = new FlashcardDeck();
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
        Optional<FlashcardDeck> flashcardDeck = flashcardDeckRepository.findById(deckId);
        if (flashcardDeck.isPresent()) {
            FlashcardDeck deck = flashcardDeck.get();
            deck.setDeleted(true);
            flashcardDeckRepository.save(deck);
            msg = "Flashcard Deck " + deckId + " is successfully deleted!";
	    } else {
	        msg = "Flashcard Deck " + deckId + " does not exist.";
	    }
	    return msg;
    }
    
}