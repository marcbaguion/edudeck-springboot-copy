package com.it332.edudeck.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.it332.edudeck.Entity.Flashcard;
import java.util.List;

public interface FlashcardRepository extends JpaRepository<Flashcard, Integer> {
    List<Flashcard> findByFlashcardDeck_deckId(int deckId);
}
