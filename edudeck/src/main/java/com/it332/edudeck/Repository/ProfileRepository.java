package com.it332.edudeck.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it332.edudeck.Entity.Profile;
import com.it332.edudeck.Entity.User;

public interface ProfileRepository extends JpaRepository<Profile, Integer>{
    Profile findByUser(User user);
}
