package com.it332.edudeck.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it332.edudeck.Entity.QuizItem;

public interface QuizItemRepository extends JpaRepository<QuizItem, Integer>{

}
