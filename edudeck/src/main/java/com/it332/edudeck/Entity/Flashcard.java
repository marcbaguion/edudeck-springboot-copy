package com.it332.edudeck.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="tblflashcard")
public class Flashcard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int flashcardId;

    private String question;
    private String answer;
    private LocalDateTime dateCreated = LocalDateTime.now();
    private boolean isDeleted = false;
    private int reviewCount = 0;

    @ManyToOne
    @JoinColumn(name = "deck_id", nullable = false)
    @JsonBackReference
    private FlashcardDeck flashcardDeck;

    public Flashcard() {}

    public Flashcard(String question, String answer, FlashcardDeck flashcardDeck) {
        this.question = question;
        this.answer = answer;
        this.flashcardDeck = flashcardDeck;
    }

    public int getFlashcardId() {
        return flashcardId;
    }

    public void setId(int flashcardId) {
        this.flashcardId = flashcardId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public FlashcardDeck getFlashcardDeck() {
        return flashcardDeck;
    }

    public void setFlashcardDeck(FlashcardDeck flashcardDeck) {
        this.flashcardDeck = flashcardDeck;
    }

    public void setFlashcardId(int flashcardId) {
        this.flashcardId = flashcardId;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    
    
}
