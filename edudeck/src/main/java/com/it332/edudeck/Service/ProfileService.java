package com.it332.edudeck.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it332.edudeck.Entity.ProfileEntity;
import com.it332.edudeck.Entity.UserEntity;
import com.it332.edudeck.Repository.ProfileRepository;
import com.it332.edudeck.Repository.UserRepository;


@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    public ProfileEntity saveProfilePicture(int userno, byte[] profilePicture) {
        UserEntity user = userRepository.findById(userno).orElse(null);
        ProfileEntity profile = profileRepository.findByUser(user);
        if (profile == null) {
            profile = new ProfileEntity();
            profile.setUser(user);
        }
        profile.setProfilePicture(profilePicture);
        return profileRepository.save(profile);
    }
}
