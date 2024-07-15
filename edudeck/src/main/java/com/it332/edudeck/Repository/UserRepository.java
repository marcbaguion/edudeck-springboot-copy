package com.it332.edudeck.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.it332.edudeck.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    User findByUsernameAndPasswordAndIsDeletedFalse(String username, String password);
    User findByUsernameAndIsDeletedFalse(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    User findByUsername(String username);
    User findByMobileNumber(String mobileNumber);
    User findByBio(String bio);
}
