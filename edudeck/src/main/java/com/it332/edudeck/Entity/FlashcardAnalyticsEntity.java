package com.it332.edudeck.Entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tblflashcardanalytics")
public class FlashcardAnalyticsEntity {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int flashcardAnalyticsId;

    private Date dateCreated;
    private Date dateLastReviewed;
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "review_session_id", nullable = false)
    private ReviewSessionEntity reviewSession;

    @ManyToOne
    @JoinColumn(name = "deck_id", nullable = false)
    private FlashcardDeckEntity deck;

    public FlashcardAnalyticsEntity() {
    }

    public FlashcardAnalyticsEntity(int flashcardAnalyticsId, Date dateCreated, Date dateLastReviewed,
            boolean isDeleted, UserEntity user, ReviewSessionEntity reviewSession, FlashcardDeckEntity deck) {
        this.flashcardAnalyticsId = flashcardAnalyticsId;
        this.dateCreated = dateCreated;
        this.dateLastReviewed = dateLastReviewed;
        this.isDeleted = isDeleted;
        this.user = user;
        this.reviewSession = reviewSession;
        this.deck = deck;
    }

    public int getFlashcardAnalyticsId() {
        return flashcardAnalyticsId;
    }

    public void setFlashcardAnalyticsId(int flashcardAnalyticsId) {
        this.flashcardAnalyticsId = flashcardAnalyticsId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateLastReviewed() {
        return dateLastReviewed;
    }

    public void setDateLastReviewed(Date dateLastReviewed) {
        this.dateLastReviewed = dateLastReviewed;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public ReviewSessionEntity getReviewSession() {
        return reviewSession;
    }

    public void setReviewSession(ReviewSessionEntity reviewSession) {
        this.reviewSession = reviewSession;
    }

    public FlashcardDeckEntity getDeck() {
        return deck;
    }

    public void setDeck(FlashcardDeckEntity deck) {
        this.deck = deck;
    }

    
}
