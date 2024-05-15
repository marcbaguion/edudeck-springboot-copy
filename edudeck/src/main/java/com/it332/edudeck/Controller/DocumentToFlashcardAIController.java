package com.it332.edudeck.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.it332.edudeck.Entity.DocumentEntity;
import com.it332.edudeck.Entity.DocumentToFlashcardAIEntity;
import com.it332.edudeck.Entity.FlashcardDeckEntity;
import com.it332.edudeck.Entity.FlashcardEntity;
import com.it332.edudeck.Entity.UserEntity;
import com.it332.edudeck.Service.DocumentToFlashcardAIService;
import com.it332.edudeck.Service.FlashcardDeckService;
import com.it332.edudeck.Service.FlashcardService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/documenttoflashcardai")
public class DocumentToFlashcardAIController {

    @Autowired
    private DocumentToFlashcardAIService documentToFlashcardAIService;

    @Autowired
    private FlashcardDeckService flashcardDeckService;

    @Autowired
    private FlashcardService flashcardService;

    @PostMapping("/createDocumentToFlashcardAI")
    public ResponseEntity<DocumentToFlashcardAIEntity> createDocumentToFlashcardAI(@RequestBody DocumentEntity document) {
        DocumentToFlashcardAIEntity createdDocumentToFlashcardAI = documentToFlashcardAIService.createDocumentToFlashcardAI(document);
        return ResponseEntity.ok(createdDocumentToFlashcardAI);
    }

    @GetMapping("/getAllDocumentToFlashcardAIs")
    public ResponseEntity<List<DocumentToFlashcardAIEntity>> getAllDocumentToFlashcardAIs() {
        return ResponseEntity.ok(documentToFlashcardAIService.getAllDocumentToFlashcardAIs());
    }

    @GetMapping("/getDocumentToFlashcardAIById/{id}")
    public ResponseEntity<DocumentToFlashcardAIEntity> getDocumentToFlashcardAIById(@PathVariable int id) {
        Optional<DocumentToFlashcardAIEntity> documentToFlashcardAI = documentToFlashcardAIService.getDocumentToFlashcardAIById(id);
        return documentToFlashcardAI.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/deleteDocumentToFlashcardAI/{id}")
    public ResponseEntity<Void> deleteDocumentToFlashcardAI(@PathVariable int id) {
        documentToFlashcardAIService.deleteDocumentToFlashcardAI(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/generateFlashcards")
    public ResponseEntity<FlashcardDeckEntity> generateFlashcards(@PathVariable int id) {
        Optional<DocumentToFlashcardAIEntity> documentToFlashcardAI = documentToFlashcardAIService.getDocumentToFlashcardAIById(id);
        if (documentToFlashcardAI.isPresent()) {
            DocumentToFlashcardAIEntity aiEntity = documentToFlashcardAI.get();
            // Assume the method generateFlashcardsFromDocument is a method that generates flashcards from a document
            List<FlashcardEntity> generatedFlashcards = generateFlashcardsFromDocument(aiEntity.getDocument());
            
            UserEntity user = aiEntity.getDocument().getUser();
            FlashcardDeckEntity flashcardDeck = flashcardDeckService.createFlashcardDeck(aiEntity.getDocument().getDocumentTitle() + " Deck", user, aiEntity);
            for (FlashcardEntity flashcard : generatedFlashcards) {
                flashcard.setFlashcardDeck(flashcardDeck);
                flashcardService.createFlashcard(flashcard.getQuestion(), flashcard.getAnswer(), flashcardDeck);
            }

            return ResponseEntity.ok(flashcardDeck);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Mock method to simulate flashcard generation
    private List<FlashcardEntity> generateFlashcardsFromDocument(DocumentEntity document) {
        // Implementation to generate flashcards from a document
        // This is a placeholder for the actual AI-based flashcard generation logic
        return List.of(
            new FlashcardEntity("Sample Question 1", "Sample Answer 1", null),
            new FlashcardEntity("Sample Question 2", "Sample Answer 2", null)
        );
    }
}
