package com.it332.edudeck.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it332.edudeck.Entity.FlashcardDeckEntity;

public interface FlashcardDeckRepository extends JpaRepository<FlashcardDeckEntity, Integer> {
    //List<FlashcardDeckEntity> findByUserUserIdAndIsDeletedFalse(int userId);
}
