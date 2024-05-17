package com.it332.edudeck.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it332.edudeck.Entity.QuizItemEntity;

public interface QuizItemRepository extends JpaRepository<QuizItemEntity, Integer>{

}
