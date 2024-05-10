package com.it332.edudeck.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it332.edudeck.Entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{

}
