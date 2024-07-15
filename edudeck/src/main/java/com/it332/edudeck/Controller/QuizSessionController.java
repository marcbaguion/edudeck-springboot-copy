package com.it332.edudeck.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.it332.edudeck.Entity.QuizSession;
import com.it332.edudeck.Service.QuizSessionService;

import java.util.List;

@RestController
@RequestMapping("/api/quizsessions")
public class QuizSessionController {

    @Autowired
    private QuizSessionService quizSessionService;

    @PostMapping("/create")
    public ResponseEntity<QuizSession> createQuizSession(@RequestBody QuizSession quizSession) {
        QuizSession createdQuizSession = quizSessionService.createQuizSession(quizSession);
        return ResponseEntity.ok(createdQuizSession);
    }

    @GetMapping("/all")
    public ResponseEntity<List<QuizSession>> getAllQuizSessions() {
        return ResponseEntity.ok(quizSessionService.getAllQuizSessions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizSession> getQuizSessionById(@PathVariable int id) {
        QuizSession quizSession = quizSessionService.getQuizSessionById(id)
                .orElseThrow(() -> new RuntimeException("QuizSession not found with id: " + id));
        return ResponseEntity.ok(quizSession);
    }

    @PutMapping("/softdelete/{id}")
    public ResponseEntity<Void> softDeleteQuizSession(@PathVariable int id) {
        boolean isDeleted = quizSessionService.softDeleteQuizSession(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
