package com.it332.edudeck.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it332.edudeck.Entity.DocumentEntity;
import com.it332.edudeck.Entity.DocumentToFlashcardAIEntity;
import com.it332.edudeck.Repository.DocumentRepository;
import com.it332.edudeck.Repository.DocumentToFlashcardAIRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentToFlashcardAIService {

    @Autowired
    private DocumentToFlashcardAIRepository documentToFlashcardAIRepository;

    @Autowired
    private DocumentRepository documentRepository;

    public DocumentToFlashcardAIEntity createDocumentToFlashcardAI(DocumentEntity document) {
        DocumentEntity managedDocument = documentRepository.findById(document.getDocumentID())
                .orElseThrow(() -> new IllegalArgumentException("Invalid document ID: " + document.getDocumentID()));
        DocumentToFlashcardAIEntity documentToFlashcardAI = new DocumentToFlashcardAIEntity(managedDocument);
        return documentToFlashcardAIRepository.save(documentToFlashcardAI);
    }

    public List<DocumentToFlashcardAIEntity> getAllDocumentToFlashcardAIs() {
        return documentToFlashcardAIRepository.findAll();
    }

    public Optional<DocumentToFlashcardAIEntity> getDocumentToFlashcardAIById(int id) {
        return documentToFlashcardAIRepository.findById(id);
    }

    public void deleteDocumentToFlashcardAI(int id) {
        Optional<DocumentToFlashcardAIEntity> documentToFlashcardAI = documentToFlashcardAIRepository.findById(id);
        if (documentToFlashcardAI.isPresent()) {
            DocumentToFlashcardAIEntity aiEntity = documentToFlashcardAI.get();
            aiEntity.setDeleted(true);
            documentToFlashcardAIRepository.save(aiEntity);
        }
    }
}
