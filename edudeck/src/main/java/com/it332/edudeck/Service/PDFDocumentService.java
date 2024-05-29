// package com.it332.edudeck.Service;

// // import java.util.HashMap;
// import java.util.Map;
// import java.util.NoSuchElementException;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import com.it332.edudeck.Entity.DocumentEntity;
// import com.it332.edudeck.Repository.DocumentRepository;

// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;
// @Service
// public class PDFDocumentService {

//     @Autowired
//     DocumentRepository documentRepository;

//     // Define a method to save the PDF content to a file and return its path
//     public Path savePDFContentToFile(int documentID) throws IOException {
//         DocumentEntity document = documentRepository.findById(documentID)
//                 .orElseThrow(() -> new NoSuchElementException("Document " + documentID + " does not exist"));

//         // Get the PDF content as a byte array
//         byte[] content = document.getFileContent();

//         // Define the directory where you want to save the file
//         String directory = "/path/to/save/pdf/files";
        
//         // Create the directory if it doesn't exist
//         Files.createDirectories(Paths.get(directory));

//         // Generate a unique file name or use the document ID as the file name
//         String fileName = "document_" + documentID + ".pdf";
        
//         // Define the path to save the file
//         Path filePath = Paths.get(directory, fileName);

//         // Write the PDF content to the file
//         Files.write(filePath, content);

//         // Return the path to the saved file
//         return filePath;
//     }

//     public DocumentEntity highlightPDF(int documentID, Map<String, String> highlights) {
//         DocumentEntity document = documentRepository.findById(documentID)
//                 .orElseThrow(() -> new NoSuchElementException("Document " + documentID + " does not exist"));

//         // Implement your highlighting logic here
//         // For example, you can store the highlights in a new column in the database

//         return documentRepository.save(document);
//     }

//     public byte[] getPDFContent(int userid, int documentID) {
//         DocumentEntity document = documentRepository.findByUser_UseridAndDocumentIDAndIsDeletedFalse(userid, documentID)
//                 .orElseThrow(() -> new NoSuchElementException("Document " + documentID + " does not exist"));
//         return document.getFileContent();
//     }

//     public DocumentEntity getDocumentByIdAndUserId(int documentID, int userid) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'getDocumentByIdAndUserId'");
//     }    
// }

    
package com.it332.edudeck.Service;

// import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it332.edudeck.Entity.DocumentEntity;
import com.it332.edudeck.Repository.DocumentRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
@Service
public class PDFDocumentService {

    @Autowired
    DocumentRepository documentRepository;

    // Define a method to save the PDF content to a file and return its path
    public Path savePDFContentToFile(int documentID) throws IOException {
        DocumentEntity document = documentRepository.findById(documentID)
                .orElseThrow(() -> new NoSuchElementException("Document " + documentID + " does not exist"));

        // Get the PDF content as a byte array
        byte[] content = document.getFileContent();

        // Define the directory where you want to save the file
        String directory = "/path/to/save/pdf/files";
        
        // Create the directory if it doesn't exist
        Files.createDirectories(Paths.get(directory));

        // Generate a unique file name or use the document ID as the file name
        String fileName = "document_" + documentID + ".pdf";
        
        // Define the path to save the file
        Path filePath = Paths.get(directory, fileName);

        // Write the PDF content to the file
        Files.write(filePath, content);

        // Return the path to the saved file
        return filePath;
    }

    public DocumentEntity highlightPDF(int documentID, Map<String, String> highlights) {
        DocumentEntity document = documentRepository.findById(documentID)
                .orElseThrow(() -> new NoSuchElementException("Document " + documentID + " does not exist"));

        // Implement your highlighting logic here
        // For example, you can store the highlights in a new column in the database

        return documentRepository.save(document);
    }

    public byte[] getPDFContent(int userid, int documentID) {
        DocumentEntity document = documentRepository.findByUser_UseridAndDocumentIDAndIsDeletedFalse(userid, documentID)
                .orElseThrow(() -> new NoSuchElementException("Document " + documentID + " does not exist"));
        return document.getFileContent();
    }

    public DocumentEntity getDocumentByIdAndUserId(int documentID, int userid) {
        return documentRepository.findByUser_UseridAndDocumentIDAndIsDeletedFalse(userid, documentID)
                .orElseThrow(() -> new NoSuchElementException("Document " + documentID + " for user " + userid + " does not exist"));
    }      
    
}

    
   