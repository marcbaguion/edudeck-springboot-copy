package com.it332.edudeck.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.it332.edudeck.Entity.FlashcardAnalyticsEntity;
import com.it332.edudeck.Service.FlashcardAnalyticsService;

import java.util.List;

@RestController
@RequestMapping("/api/flashcardanalytics")
public class FlashcardAnalyticsController {

    @Autowired
    private FlashcardAnalyticsService flashcardAnalyticsService;

    @PostMapping("/create")
    public ResponseEntity<FlashcardAnalyticsEntity> createFlashcardAnalytics(@RequestBody FlashcardAnalyticsEntity flashcardAnalytics) {
        FlashcardAnalyticsEntity createdFlashcardAnalytics = flashcardAnalyticsService.createFlashcardAnalytics(flashcardAnalytics);
        return ResponseEntity.ok(createdFlashcardAnalytics);
    }

    @GetMapping("/all")
    public ResponseEntity<List<FlashcardAnalyticsEntity>> getAllFlashcardAnalytics() {
        return ResponseEntity.ok(flashcardAnalyticsService.getAllFlashcardAnalytics());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlashcardAnalyticsEntity> getFlashcardAnalyticsById(@PathVariable int id) {
        FlashcardAnalyticsEntity flashcardAnalytics = flashcardAnalyticsService.getFlashcardAnalyticsById(id)
                .orElseThrow(() -> new RuntimeException("FlashcardAnalytics not found with id: " + id));
        return ResponseEntity.ok(flashcardAnalytics);
    }

    @PutMapping("/softdelete/{id}")
    public ResponseEntity<Void> softDeleteFlashcardAnalytics(@PathVariable int id) {
        boolean isDeleted = flashcardAnalyticsService.softDeleteFlashcardAnalytics(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

