package com.it332.edudeck.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.it332.edudeck.Entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{
    UserEntity findByUsernameAndPasswordAndIsDeletedFalse(String username, String password);
    UserEntity findByUsernameAndIsDeletedFalse(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    UserEntity findByUsername(String username);
}
