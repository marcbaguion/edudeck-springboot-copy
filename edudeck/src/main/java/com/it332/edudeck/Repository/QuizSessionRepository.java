package com.it332.edudeck.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it332.edudeck.Entity.QuizSessionEntity;

public interface QuizSessionRepository extends JpaRepository<QuizSessionEntity, Integer>{

}
