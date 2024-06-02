package com.it332.edudeck.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.it332.edudeck.Entity.FlashcardDeckEntity;
import com.it332.edudeck.Service.FlashcardDeckService;
import com.it332.edudeck.Service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/decks")
public class FlashcardDeckController {

    @Autowired
    private FlashcardDeckService flashcardDeckService;

    @Autowired
    private UserService userService;

    @PostMapping("/createFlashcardDeck")
    public ResponseEntity<FlashcardDeckEntity> createFlashcardDeck(@RequestBody FlashcardDeckEntity flashcardDeck) {
        FlashcardDeckEntity createdDeck = flashcardDeckService.createFlashcardDeck(flashcardDeck.getTitle(), flashcardDeck.getUser());
        return ResponseEntity.ok(createdDeck);
    }

    @GetMapping("/getAllFlashcardDecks")
    public ResponseEntity<List<FlashcardDeckEntity>> getAllFlashcardDecks() {
        return ResponseEntity.ok(flashcardDeckService.getAllFlashcardDecks());
    }

    @GetMapping("/getFlashcardDeckById/{id}")
    public ResponseEntity<FlashcardDeckEntity> getFlashcardDeckById(@PathVariable int id) {
        Optional<FlashcardDeckEntity> flashcardDeck = flashcardDeckService.getFlashcardDeckById(id);
        return flashcardDeck.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getDecksByUser/{userId}")
    public List<FlashcardDeckEntity> getDecksByUser(@PathVariable int userId) {
        return flashcardDeckService.getDecksByUser(userId);
    }

    // U - Update a FlashcardDeck record
	@PutMapping("/updateFlashcardDeck")
	public FlashcardDeckEntity updateDeck(@RequestParam int deckid,@RequestBody FlashcardDeckEntity newFlashcardDeckDetails) {
		return flashcardDeckService.updateDeck(deckid, newFlashcardDeckDetails);
	}

    //soft delete
	@PutMapping("/deleteFlashcardDeck/{deckId}")
	public String deleteDeck(@PathVariable int deckId) {
	    return flashcardDeckService.deleteFlashcardDeck(deckId);
	}
}
