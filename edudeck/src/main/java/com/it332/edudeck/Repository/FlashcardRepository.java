package com.it332.edudeck.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.it332.edudeck.Entity.FlashcardEntity;
import java.util.List;

public interface FlashcardRepository extends JpaRepository<FlashcardEntity, Integer> {
    List<FlashcardEntity> findByFlashcardDeck_deckId(int deckId);
}
