package com.it332.edudeck.Entity;

import jakarta.persistence.Column;
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
public class Profile {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int profileId;

    @OneToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    private User user;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] profilePicture;

    public Profile() {
    }

    public Profile(int profileId, User user, byte[] profilePicture) {
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
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

}
