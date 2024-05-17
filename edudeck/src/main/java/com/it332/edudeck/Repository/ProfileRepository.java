package com.it332.edudeck.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it332.edudeck.Entity.ProfileEntity;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Integer>{

}
