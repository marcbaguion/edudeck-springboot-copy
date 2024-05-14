package com.it332.edudeck.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.it332.edudeck.Entity.UploadDocumentEntity;
import com.it332.edudeck.Entity.UserEntity;
import com.it332.edudeck.Repository.UploadDocumentRepository;

@Service
public class UploadDocumentService {
    
    @Autowired
    UploadDocumentRepository drepo;

    // Helper method to format file size
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

    // Insert or upload a document file in tbldocument - Create
    public UploadDocumentEntity insertDocument(UploadDocumentEntity document, MultipartFile file, UserEntity user) throws IOException {
    // Set file content as byte array
    document.setFileContent(file.getBytes());

    // Set document title (you can customize this based on your requirements)
    document.setDocumentTitle(file.getOriginalFilename());

    // Set file size
    document.setFileSize(formatFileSize(file.getSize()));

    // Set the user for the document
    document.setUser(user);

    return drepo.save(document);
}

    //Read all records in tbdocument - Read
    
    public List<UploadDocumentEntity> getAllDocuments() {
        // Fetch only non-deleted documents
        return drepo.findByIsDeletedFalse();
    }
    
    public List<UploadDocumentEntity> getDocumentsByUser(UserEntity user) {
        return drepo.findByUserAndIsDeletedFalse(user);
    }

    public UploadDocumentEntity updateDocument(int documentID, String newFileName) {
        UploadDocumentEntity document = drepo.findById(documentID)
                .orElseThrow(() -> new NoSuchElementException("Document " + documentID + " does not exist"));
    
        // Update the file name
        if (newFileName != null && !newFileName.isEmpty()) {
            document.setDocumentTitle(newFileName);
        }
    
        return drepo.save(document);
    }

    // Delete the document file in tbldocument - Soft Delete
    public UploadDocumentEntity deleteDocument(int documentID) {
        UploadDocumentEntity document = drepo.findById(documentID)
                .orElseThrow(() -> new NoSuchElementException("Document " + documentID + " does not exist"));
    
        // Set isDeleted flag to true
        document.setIsDeleted(true);
    
        return drepo.save(document);
    }

    
}

// public UploadDocumentEntity replaceDocument(int documentID, MultipartFile newFile, UploadDocumentEntity newUploadDocumentDetails) {
//     UploadDocumentEntity document = drepo.findById(documentID)
//             .orElseThrow(() -> new NoSuchElementException("Document " + documentID + " does not exist"));

//     document.setDocumentTitle(newUploadDocumentDetails.getDocumentTitle());

//     // If a new file is provided, update file content and size
//     if (newFile != null) {
//         try {
//             document.setFileContent(newFile.getBytes());
//             document.setFileSize(formatFileSize(newFile.getSize()));
//         } catch (IOException e) {
//             e.printStackTrace();
//             throw new RuntimeException("Failed to update file content.");
//         }
//     }

//     return drepo.save(document);
// }

// Update document file in tbldocument - Update
    // @SuppressWarnings("finally")
    // public UploadDocumentEntity updateDocument(int documentID, UploadDocumentEntity newUploadDocumentDetails ) {
    //     UploadDocumentEntity document = new UploadDocumentEntity();
    //     try {
    //         document = drepo.findById(documentID).get();
    //         document.setDocumentTitle(newUploadDocumentDetails.getDocumentTitle());
    //         document.setFileContent(newUploadDocumentDetails.getFileContent());
    //     } catch(NoSuchElementException ex) {
    //         throw new NoSuchElementException("Document "+ documentID + "does not exist");
    //     } finally {
    //         return drepo.save(document);
    //     }
    // }]

    //@SuppressWarnings("finally")
// public UploadDocumentEntity updateDocument(int documentID, MultipartFile newFile, UploadDocumentEntity newUploadDocumentDetails) {
//     UploadDocumentEntity document = new UploadDocumentEntity();
//     try {
//         document = drepo.findById(documentID).orElseThrow(() -> new NoSuchElementException("Document " + documentID + " does not exist"));

//         document.setDocumentTitle(newUploadDocumentDetails.getDocumentTitle());

//         if (newFile != null) {
//             // Update file content and size if a new file is provided
//             document.setFileContent(newFile.getBytes());
//             document.setFileSize(formatFileSize(newFile.getSize()));
//         }
//     } catch (IOException e) {
//         // Handle IOException
//         throw new RuntimeException("Failed to update file content.");
//     } catch (NoSuchElementException ex) {
//         throw new NoSuchElementException("Document " + documentID + " does not exist");
//     } finally {
//         return drepo.save(document);
//     }
// }
// public UploadDocumentEntity updateDocument(int documentID, MultipartFile newFile, UploadDocumentEntity newUploadDocumentDetails ) {
//     UploadDocumentEntity document = drepo.findById(documentID).orElseThrow(() -> new NoSuchElementException("Document " + documentID + " does not exist"));

//     // Update document title
//     document.setDocumentTitle(newUploadDocumentDetails.getDocumentTitle());

//     if (newFile != null) {
//         try {
//             // Update file content if a new file is provided
//             document.setFileContent(newFile.getBytes());
//             document.setFileSize(formatFileSize(newFile.getSize()));
//         } catch (IOException e) {
//             // Handle IOException
//             e.printStackTrace();
//             throw new RuntimeException("Failed to update file content.");
//         }
//     }

//     return drepo.save(document);
// }

// public UploadDocumentEntity updateDocument(int documentID, MultipartFile newFile, UploadDocumentEntity newUploadDocumentDetails) {
//     // Find the existing document by documentID or throw an exception if not found
//     UploadDocumentEntity document = drepo.findById(documentID)
//             .orElseThrow(() -> new NoSuchElementException("Document " + documentID + " does not exist"));

//     // Update document title based on newUploadDocumentDetails
//     document.setDocumentTitle(newUploadDocumentDetails.getDocumentTitle());

//     // If a new file is provided, update file content and size
//     if (newFile != null) {
//         try {
//             document.setFileContent(newFile.getBytes());
//             document.setFileSize(formatFileSize(newFile.getSize()));
//         } catch (IOException e) {
//             e.printStackTrace();
//             throw new RuntimeException("Failed to update file content.");
//         }
//     }

//     // Save the updated document to the repository
//     return drepo.save(document);
// }

// public List<UploadDocumentEntity> getDocumentsByUser(UserEntity user) {
    // return drepo.findByUser(user);
    // }

     // public UploadDocumentEntity updateDocument(int documentID, String newFileName, MultipartFile newFile) throws IOException {
    //     List<UploadDocumentEntity> documents = drepo.findByDocumentIDAndIsDeletedFalse(documentID);

    //     if (!documents.isEmpty()) {
    //         UploadDocumentEntity document = documents.get(0);

    //         // Update the file name
    //         if (newFileName != null && !newFileName.isEmpty()) {
    //             document.setDocumentTitle(newFileName);
    //         }

    //         // Update the file content if a new file is provided
    //         if (newFile != null && !newFile.isEmpty()) {
    //             document.setFileContent(newFile.getBytes());
    //             document.setFileSize(formatFileSize(newFile.getSize()));
    //         }

    //         // Save the changes to the database
    //         return drepo.save(document);
    //     } else {
    //         throw new NoSuchElementException("Document " + documentID + " does not exist");
    //     }
    // }
