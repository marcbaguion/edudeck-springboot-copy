package com.it332.edudeck.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;


@Entity
@Table(name="tbluser")
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userid;
	
	@Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String name = "";
    private String mobileNumber;
    private LocalDateTime dateCreated = LocalDateTime.now();
    private String subscription = "Free";
    private String bio;
    private byte[] profilePicture;
	private boolean isDeleted = false;

	@OneToMany(mappedBy = "user")
    private List<DocumentEntity> documents;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<FlashcardDeckEntity> flashcardDecks;

	public UserEntity() {
		super();
	}

	public UserEntity(int userid, String username, String email) {
		super();
		this.userid = userid;
		this.username = username;
		this.email = email;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	 public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getMobileNumber() {
	        return mobileNumber;
	    }

	    public void setMobileNumber(String mobileNumber) {
	        this.mobileNumber = mobileNumber;
	    }

	    public LocalDateTime getDateCreated() {
	        return dateCreated;
	    }

	    public void setDateCreated(LocalDateTime dateCreated) {
	        this.dateCreated = dateCreated;
	    }

	    public String getSubscription() {
	        return subscription;
	    }

	    public void setSubscription(String subscription) {
	        this.subscription = subscription;
	    }

	    public String getBio() {
	        return bio;
	    }

	    public void setBio(String bio) {
	        this.bio = bio;
	    }

	    public byte[] getProfilePicture() {
	        return profilePicture;
	    }

	    public void setProfilePicture(byte[] profilePicture) {
	        this.profilePicture = profilePicture;
	    }

		public boolean isDeleted() {
			return isDeleted;
		}

		public void setDeleted(boolean isDeleted) {
			this.isDeleted = isDeleted;
		}

		public Set<FlashcardDeckEntity> getFlashcardDecks() {
			return flashcardDecks;
		}

		public void setFlashcardDecks(Set<FlashcardDeckEntity> flashcardDecks) {
			this.flashcardDecks = flashcardDecks;
		}

		
}
