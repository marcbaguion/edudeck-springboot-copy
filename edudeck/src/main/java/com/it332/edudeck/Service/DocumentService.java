// package com.it332.edudeck.Service;

// import java.io.IOException;
// import java.util.List;
// import java.util.NoSuchElementException;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.web.multipart.MultipartFile;

// import com.it332.edudeck.Entity.DocumentEntity;
// import com.it332.edudeck.Entity.UserEntity;
// import com.it332.edudeck.Repository.DocumentRepository;

// @Service
// public class DocumentService {

//     @Autowired
//     DocumentRepository drepo;

//     private String formatFileSize(long size) {
//         String[] units = {"B", "KB", "MB", "GB", "TB"};
//         int unitIndex = 0;
//         double fileSize = size;

//         while (fileSize > 1024 && unitIndex < units.length - 1) {
//             fileSize /= 1024;
//             unitIndex++;
//         }

//         return String.format("%.2f %s", fileSize, units[unitIndex]);
//     }

//     public DocumentEntity insertDocument(DocumentEntity document, MultipartFile file, UserEntity user) throws IOException {
//         document.setFileContent(file.getBytes());
//         document.setDocumentTitle(file.getOriginalFilename());
//         document.setFileSize(formatFileSize(file.getSize()));
//         document.setUser(user);
//         return drepo.save(document);
//     }

//     public List<DocumentEntity> getAllDocuments() {
//         return drepo.findByIsDeletedFalse();
//     }

//     public List<DocumentEntity> getDocumentsByUser(UserEntity user) {
//         return drepo.findByUserAndIsDeletedFalse(user);
//     }

//     public DocumentEntity getDocumentById(int documentID) {
//         return drepo.findById(documentID)
//                 .orElseThrow(() -> new NoSuchElementException("Document " + documentID + " does not exist"));
//     }

//     public DocumentEntity updateDocument(int documentID, String newFileName) {
//         DocumentEntity document = getDocumentById(documentID);
//         if (newFileName != null && !newFileName.isEmpty()) {
//             document.setDocumentTitle(newFileName);
//         }
//         return drepo.save(document);
//     }

//     public DocumentEntity deleteDocument(int documentID) {
//         DocumentEntity document = getDocumentById(documentID);
//         document.setIsDeleted(true);
//         return drepo.save(document);
//     }
// }

// package com.it332.edudeck.Service;

// import java.io.IOException;
// import java.util.List;
// import java.util.NoSuchElementException;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.web.multipart.MultipartFile;

// import com.it332.edudeck.Entity.DocumentEntity;
// import com.it332.edudeck.Entity.UserEntity;
// import com.it332.edudeck.Repository.DocumentRepository;

// @Service
// public class DocumentService {

//     @Autowired
//     DocumentRepository drepo;

//     private String formatFileSize(long size) {
//         String[] units = {"B", "KB", "MB", "GB", "TB"};
//         int unitIndex = 0;
//         double fileSize = size;

//         while (fileSize > 1024 && unitIndex < units.length - 1) {
//             fileSize /= 1024;
//             unitIndex++;
//         }

//         return String.format("%.2f %s", fileSize, units[unitIndex]);
//     }

//     public DocumentEntity insertDocument(DocumentEntity document, MultipartFile file, UserEntity user) throws IOException {
//         document.setFileContent(file.getBytes());
//         document.setDocumentTitle(file.getOriginalFilename());
//         document.setFileSize(formatFileSize(file.getSize()));
//         document.setUser(user);
//         return drepo.save(document);
//     }

//     public List<DocumentEntity> getAllDocuments() {
//         return drepo.findByIsDeletedFalse();
//     }

//     public List<DocumentEntity> getDocumentsByUser(UserEntity user) {
//         return drepo.findByUserAndIsDeletedFalse(user);
//     }

//     public DocumentEntity getDocumentById(int documentID) {
//         return drepo.findById(documentID)
//                 .orElseThrow(() -> new NoSuchElementException("Document " + documentID + " does not exist"));
//     }

//     public DocumentEntity updateDocument(int documentID, String newFileName) {
//         DocumentEntity document = getDocumentById(documentID);
//         if (newFileName != null && !newFileName.isEmpty()) {
//             document.setDocumentTitle(newFileName);
//         }
//         return drepo.save(document);
//     }

//     public DocumentEntity deleteDocument(int documentID) {
//         DocumentEntity document = getDocumentById(documentID);
//         document.setIsDeleted(true);
//         return drepo.save(document);
//     }

//     public byte[] getFileContentByFileName(String fileName) throws IOException {
//     DocumentEntity document = drepo.findByDocumentTitleAndIsDeletedFalse(fileName)
//             .orElseThrow(() -> new NoSuchElementException("Document " + fileName + " does not exist"));
//     return document.getFileContent();
// }

// }




package com.it332.edudeck.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.it332.edudeck.Entity.DocumentEntity;
import com.it332.edudeck.Entity.UserEntity;
import com.it332.edudeck.Repository.DocumentRepository;

@Service
public class DocumentService {

    @Autowired
    DocumentRepository drepo;

    private String formatFileSize(long size) {
        String[] units = {"B", "KB", "MB", "GB", "TB"};
        int unitIndex = 0;
        double fileSize = size;

        while (fileSize > 1024 && unitIndex < units.length - 1) {
            fileSize /= 1024;
            unitIndex++;
        }

        return String.format("%.2f %s", fileSize, units[unitIndex]);
    }

    public DocumentEntity insertDocument(DocumentEntity document, MultipartFile file, UserEntity user) throws IOException {
        document.setFileContent(file.getBytes());
        document.setDocumentTitle(file.getOriginalFilename());
        document.setFileSize(formatFileSize(file.getSize()));
        document.setUser(user);
        return drepo.save(document);
    }

    public List<DocumentEntity> getAllDocuments() {
        return drepo.findByIsDeletedFalse();
    }

    public List<DocumentEntity> getDocumentsByUser(UserEntity user) {
        return drepo.findByUserAndIsDeletedFalse(user);
    }

    public DocumentEntity getDocumentById(int documentID) {
        return drepo.findById(documentID)
                .orElseThrow(() -> new NoSuchElementException("Document " + documentID + " does not exist"));
    }

    public DocumentEntity updateDocument(int documentID, String newFileName) {
        DocumentEntity document = getDocumentById(documentID);
        if (newFileName != null && !newFileName.isEmpty()) {
            document.setDocumentTitle(newFileName);
        }
        return drepo.save(document);
    }

    public DocumentEntity deleteDocument(int documentID) {
        DocumentEntity document = getDocumentById(documentID);
        document.setIsDeleted(true);
        return drepo.save(document);
    }

    public byte[] getFileContentByDocumentID(int documentID) throws IOException {
        DocumentEntity document = getDocumentById(documentID);
        return document.getFileContent();
    }
}
