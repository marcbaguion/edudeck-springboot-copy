package com.it332.edudeck.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name="tbldocumenttoflashcardai")
public class DocumentToFlashcardAIEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int AIId;

    @OneToOne(mappedBy = "documentToFlashcardAI")
    private FlashcardDeckEntity flashcardDeck;

    @ManyToOne
    @JoinColumn(name = "document_id", nullable = false)
    private DocumentEntity document;

    private LocalDateTime dateCreated = LocalDateTime.now();
    private boolean isDeleted = false;

    public DocumentToFlashcardAIEntity() {}

    public DocumentToFlashcardAIEntity(DocumentEntity document) {
        this.document = document;
    }

    public int getDocumentToFlashcardAIId() {
        return AIId;
    }

    public void setDocumentToFlashcardAIId(int documentToFlashcardAIId) {
        AIId = documentToFlashcardAIId;
    }

    public FlashcardDeckEntity getFlashcardDeck() {
        return flashcardDeck;
    }

    public void setFlashcardDeck(FlashcardDeckEntity flashcardDeck) {
        this.flashcardDeck = flashcardDeck;
    }

    public DocumentEntity getDocument() {
        return document;
    }

    public void setDocument(DocumentEntity document) {
        this.document = document;
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

    
}
