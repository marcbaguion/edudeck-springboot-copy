package com.it332.edudeck.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it332.edudeck.Entity.FlashcardDeck;
import com.it332.edudeck.Entity.User;

public interface FlashcardDeckRepository extends JpaRepository<FlashcardDeck, Integer> {
    List<FlashcardDeck> findByUserAndIsDeletedFalse(User user);
}
