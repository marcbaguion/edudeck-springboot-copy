package com.it332.edudeck.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it332.edudeck.Entity.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Integer>{

}
