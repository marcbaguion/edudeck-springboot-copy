package com.it332.edudeck.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it332.edudeck.Entity.FlashcardDeckEntity;

public interface FlashcardDeckRepository extends JpaRepository<FlashcardDeckEntity, Integer> {

}
