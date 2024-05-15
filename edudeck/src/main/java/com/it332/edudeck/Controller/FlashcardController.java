package com.it332.edudeck.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.it332.edudeck.Entity.FlashcardEntity;
import com.it332.edudeck.Service.FlashcardService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/flashcards")
public class FlashcardController {

    @Autowired
    private FlashcardService flashcardService;

    @PostMapping("/createFlashcard")
    public ResponseEntity<FlashcardEntity> createFlashcard(@RequestBody FlashcardEntity flashcard) {
        FlashcardEntity createdFlashcard = flashcardService.createFlashcard(flashcard.getQuestion(), flashcard.getAnswer(), flashcard.getFlashcardDeck());
        return ResponseEntity.ok(createdFlashcard);
    }

    @GetMapping("/getAllFlashcards")
    public ResponseEntity<List<FlashcardEntity>> getAllFlashcards() {
        return ResponseEntity.ok(flashcardService.getAllFlashcards());
    }

    @GetMapping("/getFlashcardById/{id}")
    public ResponseEntity<FlashcardEntity> getFlashcardById(@PathVariable int id) {
        Optional<FlashcardEntity> flashcard = flashcardService.getFlashcardById(id);
        return flashcard.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/deleteFlashcard/{id}")
    public ResponseEntity<Void> deleteFlashcard(@PathVariable int id) {
        flashcardService.deleteFlashcard(id);
        return ResponseEntity.noContent().build();
    }
}
