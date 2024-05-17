package com.it332.edudeck.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it332.edudeck.Entity.ProfileEntity;
import com.it332.edudeck.Repository.ProfileRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public ProfileEntity createProfile(ProfileEntity profile) {
        return profileRepository.save(profile);
    }

    public List<ProfileEntity> getAllProfiles() {
        return profileRepository.findAll();
    }

    public Optional<ProfileEntity> getProfileById(int id) {
        return profileRepository.findById(id);
    }

    public boolean softDeleteProfile(int id) {
        Optional<ProfileEntity> profileOptional = profileRepository.findById(id);
        if (profileOptional.isPresent()) {
            ProfileEntity profile = profileOptional.get();
            profile.setDeleted(true);
            profileRepository.save(profile);
            return true;
        } else {
            return false;
        }
    }
}
