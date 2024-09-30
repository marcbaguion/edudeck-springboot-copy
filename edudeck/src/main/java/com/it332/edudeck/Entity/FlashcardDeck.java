package com.it332.edudeck.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="tbldeck")
public class FlashcardDeck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deck_id") 
    private int deckId;

    private String title;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "document_id")
    private Document document;

    @OneToMany(mappedBy = "flashcardDeck", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Flashcard> flashcards;


    private LocalDateTime dateCreated = LocalDateTime.now();
    private boolean isDeleted = false;
    
    public FlashcardDeck() {}

    public FlashcardDeck(String title, User user) {
        this.title = title;
        this.user = user;
    }

    public int getDeckId() {
        return deckId;
    }

    public void setDeckId(int deckId) {
        this.deckId = deckId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Flashcard> getFlashcards() {
        return flashcards;
    }

    public void setFlashcards(Set<Flashcard> flashcards) {
        this.flashcards = flashcards;
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

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
    
}