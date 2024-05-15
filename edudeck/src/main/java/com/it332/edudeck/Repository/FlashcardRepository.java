package com.it332.edudeck.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it332.edudeck.Entity.FlashcardEntity;

public interface FlashcardRepository extends JpaRepository<FlashcardEntity, Integer>{

}
