package com.it332.edudeck.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tblprofile")
public class ProfileEntity {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int profileId;

    @OneToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    private UserEntity user;

    @Lob
    private byte[] profilePicture;

    public ProfileEntity() {
    }

    public ProfileEntity(int profileId, UserEntity user, byte[] profilePicture) {
        this.profileId = profileId;
        this.user = user;
        this.profilePicture = profilePicture;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }
    
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

}
