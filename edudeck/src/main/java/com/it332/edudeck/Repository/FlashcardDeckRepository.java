package com.it332.edudeck.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it332.edudeck.Entity.FlashcardDeckEntity;
import com.it332.edudeck.Entity.UserEntity;

public interface FlashcardDeckRepository extends JpaRepository<FlashcardDeckEntity, Integer> {
    List<FlashcardDeckEntity> findByUserAndIsDeletedFalse(UserEntity user);
}
