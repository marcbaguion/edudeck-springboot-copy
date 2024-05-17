package com.it332.edudeck.Entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tblquizai")
public class DocumentToQuizAIEntity {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int quizAIId;

     @OneToOne
    @JoinColumn(name = "document_id", nullable = false)
    private DocumentEntity document;

    @OneToOne
    @JoinColumn(name = "deck_id", nullable = false)
    private FlashcardDeckEntity deck;

    private Date dateCreated;
    private boolean isDeleted;
    
    public DocumentToQuizAIEntity() {
    }

    public DocumentToQuizAIEntity(int quizAIId, DocumentEntity document, FlashcardDeckEntity deck, Date dateCreated,
            boolean isDeleted) {
        this.quizAIId = quizAIId;
        this.document = document;
        this.deck = deck;
        this.dateCreated = dateCreated;
        this.isDeleted = isDeleted;
    }

    public int getQuizAIId() {
        return quizAIId;
    }

    public void setQuizAIId(int quizAIId) {
        this.quizAIId = quizAIId;
    }

    public DocumentEntity getDocument() {
        return document;
    }

    public void setDocument(DocumentEntity document) {
        this.document = document;
    }

    public FlashcardDeckEntity getDeck() {
        return deck;
    }

    public void setDeck(FlashcardDeckEntity deck) {
        this.deck = deck;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    
}
