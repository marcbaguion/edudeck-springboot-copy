package com.it332.edudeck.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.it332.edudeck.Entity.QuizEntity;
import com.it332.edudeck.Service.QuizService;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<QuizEntity> createQuiz(@RequestBody QuizEntity quiz) {
        QuizEntity createdQuiz = quizService.createQuiz(quiz);
        return ResponseEntity.ok(createdQuiz);
    }

    @GetMapping("/all")
    public ResponseEntity<List<QuizEntity>> getAllQuizzes() {
        return ResponseEntity.ok(quizService.getAllQuizzes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuizEntity> getQuizById(@PathVariable int id) {
        QuizEntity quiz = quizService.getQuizById(id)
                .orElseThrow(() -> new RuntimeException("Quiz not found with id: " + id));
        return ResponseEntity.ok(quiz);
    }

    @PutMapping("/softdelete/{id}")
    public ResponseEntity<Void> softDeleteQuiz(@PathVariable int id) {
        boolean isDeleted = quizService.softDeleteQuiz(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
