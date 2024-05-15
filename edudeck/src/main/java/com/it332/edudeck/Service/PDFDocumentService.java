package com.it332.edudeck.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it332.edudeck.Entity.DocumentEntity;
import com.it332.edudeck.Repository.DocumentRepository;

@Service
public class PDFDocumentService {
    
    @Autowired
    DocumentRepository documentRepository;

    // // Method to extract text from a document
    // public String extractTextFromDocument(int documentId) {
    //     UploadDocumentEntity document = documentRepository.findById(documentId)
    //         .orElseThrow(() -> new NoSuchElementException("Document " + documentId + " not found"));

    //     // Use a library like Apache PDFBox to extract text from the document content
    //     String extractedText = PdfTextExtractor.extractText(document.getFileContent());

    //     // Update the document entity with the extracted text
    //     document.setExtractedText(extractedText);
    //     documentRepository.save(document);

    //     return extractedText;
    // }

    // // Method to generate flashcards from extracted text
    // public Map<String, String> generateFlashcards(String extractedText) {
    //     // Logic to generate flashcards from extracted text
    //     // For example, split the text into sentences and create front-back pairs
    //     // Here's a simplified example:
    //     String[] sentences = extractedText.split("\\.\\s*");
    //     Map<String, String> flashcards = new HashMap<>();
    //     for (String sentence : sentences) {
    //         // For each sentence, use it as both front and back of the flashcard
    //         flashcards.put(sentence, sentence);
    //     }
    //     return flashcards;
    // }
}