package com.it332.edudeck.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.it332.edudeck.Entity.QuizAnalyticsEntity;
import com.it332.edudeck.Service.QuizAnalyticsService;

import java.util.List;

@RestController
@RequestMapping("/api/quizanalytics")
public class QuizAnalyticsController {

    @Autowired
    private QuizAnalyticsService quizAnalyticsService;

    @PostMapping("/create")
    public ResponseEntity<QuizAnalyticsEntity> createQuizAnalytics(@RequestBody QuizAnalyticsEntity quizAnalytics) {
        QuizAnalyticsEntity createdQuizAnalytics = quizAnalyticsService.createQuizAnalytics(quizAnalytics);
        return ResponseEntity.ok(createdQuizAnalytics);
    }

    @GetMapping("/all")
    public ResponseEntity<List<QuizAnalyticsEntity>> getAllQuizAnalytics() {
        return ResponseEntity.ok(quizAnalyticsService.getAllQuizAnalytics());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizAnalyticsEntity> getQuizAnalyticsById(@PathVariable int id) {
        QuizAnalyticsEntity quizAnalytics = quizAnalyticsService.getQuizAnalyticsById(id)
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

