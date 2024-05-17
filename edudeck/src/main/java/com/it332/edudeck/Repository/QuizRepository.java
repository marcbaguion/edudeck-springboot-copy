package com.it332.edudeck.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it332.edudeck.Entity.QuizEntity;

public interface QuizRepository extends JpaRepository<QuizEntity, Integer>{

}
