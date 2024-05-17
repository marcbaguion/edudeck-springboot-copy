package com.it332.edudeck.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it332.edudeck.Entity.HighlightEntity;
import com.it332.edudeck.Repository.HighlightRepository;

import java.util.List;
import java.util.Optional;

@Service
public class HighlightService {

    @Autowired
    private HighlightRepository highlightRepository;

    public HighlightEntity createHighlight(HighlightEntity highlight) {
        return highlightRepository.save(highlight);
    }

    public List<HighlightEntity> getAllHighlights() {
        return highlightRepository.findAll();
    }

    public Optional<HighlightEntity> getHighlightById(int id) {
        return highlightRepository.findById(id);
    }

    public boolean softDeleteHighlight(int id) {
        Optional<HighlightEntity> highlightOptional = highlightRepository.findById(id);
        if (highlightOptional.isPresent()) {
            HighlightEntity highlight = highlightOptional.get();
            highlight.setDeleted(true);
            highlightRepository.save(highlight);
            return true;
        } else {
            return false;
        }
    }
}

