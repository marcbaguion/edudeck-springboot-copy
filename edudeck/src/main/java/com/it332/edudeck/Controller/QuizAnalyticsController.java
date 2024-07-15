package com.it332.edudeck.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.it332.edudeck.Entity.QuizAnalytics;
import com.it332.edudeck.Service.QuizAnalyticsService;

import java.util.List;

@RestController
@RequestMapping("/api/quizanalytics")
public class QuizAnalyticsController {

    @Autowired
    private QuizAnalyticsService quizAnalyticsService;

    @PostMapping("/create")
    public ResponseEntity<QuizAnalytics> createQuizAnalytics(@RequestBody QuizAnalytics quizAnalytics) {
        QuizAnalytics createdQuizAnalytics = quizAnalyticsService.createQuizAnalytics(quizAnalytics);
        return ResponseEntity.ok(createdQuizAnalytics);
    }

    @GetMapping("/all")
    public ResponseEntity<List<QuizAnalytics>> getAllQuizAnalytics() {
        return ResponseEntity.ok(quizAnalyticsService.getAllQuizAnalytics());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizAnalytics> getQuizAnalyticsById(@PathVariable int id) {
        QuizAnalytics quizAnalytics = quizAnalyticsService.getQuizAnalyticsById(id)
                .orElseThrow(() -> new RuntimeException("QuizAnalytics not found with id: " + id));
        return ResponseEntity.ok(quizAnalytics);
    }

    @PutMapping("/softdelete/{id}")
    public ResponseEntity<Void> softDeleteQuizAnalytics(@PathVariable int id) {
        boolean isDeleted = quizAnalyticsService.softDeleteQuizAnalytics(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

