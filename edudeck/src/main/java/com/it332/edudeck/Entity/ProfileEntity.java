package com.it332.edudeck.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tblprofile")
public class ProfileEntity {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int profileId;

    private boolean isDeleted = false;

    public ProfileEntity() {
    }

    public ProfileEntity(int profileId, boolean isDeleted) {
        this.profileId = profileId;
        this.isDeleted = isDeleted;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    

}
