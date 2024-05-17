package com.it332.edudeck.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.it332.edudeck.Entity.HighlightEntity;
import com.it332.edudeck.Service.HighlightService;

import java.util.List;

@RestController
@RequestMapping("/api/highlights")
public class HighlightController {

    @Autowired
    private HighlightService highlightService;

    @PostMapping("/create")
    public ResponseEntity<HighlightEntity> createHighlight(@RequestBody HighlightEntity highlight) {
        HighlightEntity createdHighlight = highlightService.createHighlight(highlight);
        return ResponseEntity.ok(createdHighlight);
    }

    @GetMapping("/all")
    public ResponseEntity<List<HighlightEntity>> getAllHighlights() {
        return ResponseEntity.ok(highlightService.getAllHighlights());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HighlightEntity> getHighlightById(@PathVariable int id) {
        HighlightEntity highlight = highlightService.getHighlightById(id)
                .orElseThrow(() -> new RuntimeException("Highlight not found with id: " + id));
        return ResponseEntity.ok(highlight);
    }

    @PutMapping("/softdelete/{id}")
    public ResponseEntity<Void> softDeleteHighlight(@PathVariable int id) {
        boolean isDeleted = highlightService.softDeleteHighlight(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
