package com.it332.edudeck.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.it332.edudeck.Entity.DocumentToQuizAIEntity;
import com.it332.edudeck.Service.DocumentToQuizAIService;

import java.util.List;

@RestController
@RequestMapping("/api/documenttoquizais")
public class DocumentToQuizAIController {

    @Autowired
    private DocumentToQuizAIService documentToQuizAIService;

    @PostMapping("/create")
    public ResponseEntity<DocumentToQuizAIEntity> createDocumentToQuizAI(@RequestBody DocumentToQuizAIEntity documentToQuizAI) {
        DocumentToQuizAIEntity createdDocumentToQuizAI = documentToQuizAIService.createDocumentToQuizAI(documentToQuizAI);
        return ResponseEntity.ok(createdDocumentToQuizAI);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DocumentToQuizAIEntity>> getAllDocumentToQuizAIs() {
        return ResponseEntity.ok(documentToQuizAIService.getAllDocumentToQuizAIs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DocumentToQuizAIEntity> getDocumentToQuizAIById(@PathVariable int id) {
        DocumentToQuizAIEntity documentToQuizAI = documentToQuizAIService.getDocumentToQuizAIById(id)
                .orElseThrow(() -> new RuntimeException("DocumentToQuizAI not found with id: " + id));
        return ResponseEntity.ok(documentToQuizAI);
    }

    @PutMapping("/softdelete/{id}")
    public ResponseEntity<Void> softDeleteDocumentToQuizAI(@PathVariable int id) {
        boolean isDeleted = documentToQuizAIService.softDeleteDocumentToQuizAI(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

