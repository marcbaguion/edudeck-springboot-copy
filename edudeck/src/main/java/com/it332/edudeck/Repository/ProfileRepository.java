package com.it332.edudeck.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it332.edudeck.Entity.ProfileEntity;
import com.it332.edudeck.Entity.UserEntity;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Integer>{
    ProfileEntity findByUser(UserEntity user);
}
