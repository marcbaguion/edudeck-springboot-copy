package com.it332.edudeck.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tblreviewsession")
public class ReviewSession {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int reviewSessionId;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "deck_id", nullable = false)
    private FlashcardDeck flashcardDeck;

    @OneToOne
    @JoinColumn(name = "current_flashcard_id")
    private Flashcard currentFlashcard;

    public ReviewSession() {
    }

    public ReviewSession(int reviewSessionId, LocalDateTime startTime, LocalDateTime endTime,
                         FlashcardDeck flashcardDeck) {
        this.reviewSessionId = reviewSessionId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.flashcardDeck = flashcardDeck;
    }

    public int getReviewSessionId() {
        return reviewSessionId;
    }

    public void setReviewSessionId(int reviewSessionId) {
        this.reviewSessionId = reviewSessionId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public FlashcardDeck getFlashcardDeck() {
        return flashcardDeck;
    }

    public void setFlashcardDeck(FlashcardDeck flashcardDeck) {
        this.flashcardDeck = flashcardDeck;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Flashcard getCurrentFlashcard() {
        return currentFlashcard;
    }

    public void setCurrentFlashcard(Flashcard currentFlashcard) {
        this.currentFlashcard = currentFlashcard;
    }

    
    
}
