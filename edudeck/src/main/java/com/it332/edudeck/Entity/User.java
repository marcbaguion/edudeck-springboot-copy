package com.it332.edudeck.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name="tbluser")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userid;
	
	@Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
	
	private String bio;
	private String mobileNumber;

	private boolean isDeleted = false;

	@Lob
	@Column(name = "profile_picture", columnDefinition="BLOB")
	private byte[] profilePicture;

	@Column(name = "creation_date")
	private LocalDate creationDate;

	@OneToMany(mappedBy = "user")
    private List<Document> documents;

	@OneToMany(mappedBy = "user")
    private List<FlashcardDeck> decks;

	public User() {
		super();
	}

	public User(int userid, String username, String password, String email, String bio, String mobileNumber, boolean isDeleted, byte[] profilePicture, LocalDate creationDate) {
		super();
		this.userid = userid;
		this.username = username;
		this.email = email;
		this.password = password;
		this.bio = bio;
		this.mobileNumber = mobileNumber;
        this.isDeleted = isDeleted;
		this.profilePicture = profilePicture;
		this.creationDate = creationDate;
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

		public boolean isDeleted() {
			return isDeleted;
		}

		public void setDeleted(boolean isDeleted) {
			this.isDeleted = isDeleted;
		}

		public String getBio() {
			return bio;
		}

		public void setBio(String bio) {
			this.bio = bio;
		}

		public String getMobileNumber() {
			return mobileNumber;
		}

		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}

		public byte[] getProfilePicture() {
			return profilePicture;
		}

		public void setProfilePicture(byte[] profilePicture) {
			this.profilePicture = profilePicture;
		}

		public LocalDate getCreationDate() {
			return creationDate;
		}

		public void setCreationDate(LocalDate creationDate) {
			this.creationDate = creationDate;
		}

}
