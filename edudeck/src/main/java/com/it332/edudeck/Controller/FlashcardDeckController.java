package com.it332.edudeck.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.it332.edudeck.Entity.FlashcardDeckEntity;
import com.it332.edudeck.Service.FlashcardDeckService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/decks")
public class FlashcardDeckController {

    @Autowired
    private FlashcardDeckService flashcardDeckService;

    @PostMapping("/createFlashcardDeck")
    public ResponseEntity<FlashcardDeckEntity> createFlashcardDeck(@RequestBody FlashcardDeckEntity flashcardDeck) {
        FlashcardDeckEntity createdDeck = flashcardDeckService.createFlashcardDeck(flashcardDeck.getTitle(), flashcardDeck.getUser(), flashcardDeck.getDocumentToFlashcardAI());
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

    @DeleteMapping("/deleteFlashcardDeck/{id}")
    public ResponseEntity<Void> deleteFlashcardDeck(@PathVariable int id) {
        flashcardDeckService.deleteFlashcardDeck(id);
        return ResponseEntity.noContent().build();
    }
}
