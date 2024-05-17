package com.it332.edudeck.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it332.edudeck.Entity.DocumentToQuizAIEntity;
import com.it332.edudeck.Repository.DocumentToQuizAIRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentToQuizAIService {

    @Autowired
    private DocumentToQuizAIRepository documentToQuizAIRepository;

    public DocumentToQuizAIEntity createDocumentToQuizAI(DocumentToQuizAIEntity documentToQuizAI) {
        return documentToQuizAIRepository.save(documentToQuizAI);
    }

    public List<DocumentToQuizAIEntity> getAllDocumentToQuizAIs() {
        return documentToQuizAIRepository.findAll();
    }

    public Optional<DocumentToQuizAIEntity> getDocumentToQuizAIById(int id) {
        return documentToQuizAIRepository.findById(id);
    }

    public boolean softDeleteDocumentToQuizAI(int id) {
        Optional<DocumentToQuizAIEntity> documentToQuizAIOptional = documentToQuizAIRepository.findById(id);
        if (documentToQuizAIOptional.isPresent()) {
            DocumentToQuizAIEntity documentToQuizAI = documentToQuizAIOptional.get();
            documentToQuizAI.setDeleted(true);
            documentToQuizAIRepository.save(documentToQuizAI);
            return true;
        } else {
            return false;
        }
    }
}
