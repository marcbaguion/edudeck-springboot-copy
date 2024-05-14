package com.it332.edudeck.Controller;

import java.io.IOException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import com.it332.edudeck.Entity.UploadDocumentEntity;
import com.it332.edudeck.Entity.UserEntity;
import com.it332.edudeck.Service.UploadDocumentService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/document")
public class UploadDocumentController {

    @Autowired
    UploadDocumentService dserv;

    @PostMapping("/upload/{userid}")
    public ResponseEntity<UploadDocumentEntity> insertDocument(@RequestPart("document") UploadDocumentEntity document, @RequestPart("file") MultipartFile file, UserEntity user) throws IOException {

        try {
            UploadDocumentEntity savedDocument = dserv.insertDocument(document, file, user);
            return ResponseEntity.ok(savedDocument);
        } catch (MaxUploadSizeExceededException e) {
            // Handle file size exceeded error
            return ResponseEntity.status(413).build(); // 413 Request Entity Too Large
        }
    }

    @GetMapping("/files/{userid}")
    public ResponseEntity<List<UploadDocumentEntity>> getDocumentsByUser(@PathVariable int userid) {
        UserEntity user = new UserEntity();
        user.setUserid(userid);

        List<UploadDocumentEntity> uploadedFiles = dserv.getDocumentsByUser(user);
        return ResponseEntity.ok(uploadedFiles);
    }

    @PutMapping("/update/{documentID}")
    public ResponseEntity<UploadDocumentEntity> updateDocument(@PathVariable int documentID, @RequestParam(name = "newFileName", required = false) String newFileName) {
        UploadDocumentEntity updatedDocument = dserv.updateDocument(documentID, newFileName);
        return ResponseEntity.ok(updatedDocument);
    }

    @DeleteMapping("/delete/{documentID}")
    public ResponseEntity<UploadDocumentEntity> deleteDocument(@PathVariable int documentID) {
        UploadDocumentEntity softDeletedDocument = dserv.deleteDocument(documentID);
        return ResponseEntity.ok(softDeletedDocument);
    }


}

//     @PutMapping("/update/{documentID}")
//     public ResponseEntity<UploadDocumentEntity> updateDocument(@PathVariable int documentID, @RequestParam(name = "file", required = false) MultipartFile newFile, @RequestPart("document") UploadDocumentEntity newUploadDocumentEntity) {
//         UploadDocumentEntity updatedDocument = dserv.updateDocument(documentID, newFile, newUploadDocumentEntity);
//         return ResponseEntity.ok(updatedDocument);
// }


    // @PutMapping("/replace/{documentID}")
    // public ResponseEntity<UploadDocumentEntity> replaceDocument(@PathVariable int documentID, @RequestParam(name = "file", required = false) MultipartFile newFile, @RequestPart("document") UploadDocumentEntity newUploadDocumentEntity) {
    //     UploadDocumentEntity replacedDocument = dserv.replaceDocument(documentID, newFile, newUploadDocumentEntity);
    //     return ResponseEntity.ok(replacedDocument);
    // }

// @PutMapping("/update")
    // public UploadDocumentEntity updateDocument(@RequestParam int documentID, @RequestBody UploadDocumentEntity newUploadDocumentEntity) {
    //     return dserv.updateDocument(documentID);
    // }

//     @PutMapping("/update/{documentID}")
// public UploadDocumentEntity updateDocument(@PathVariable int documentID, @RequestParam(name = "file", required = false) MultipartFile newFile, @RequestPart("document") UploadDocumentEntity newUploadDocumentEntity) {
//     return dserv.updateDocument(documentID);
// }

 // @PutMapping("/update/{documentID}")
    // public ResponseEntity<UploadDocumentEntity> updateDocument(
    //         @PathVariable int documentID,
    //         @RequestParam(name = "newFileName", required = false) String newFileName,
    //         @RequestPart(value = "newFile", required = false) MultipartFile newFile) {
    //     try {
    //         UploadDocumentEntity updatedDocument = dserv.updateDocument(documentID, newFileName, newFile);
    //         return ResponseEntity.ok(updatedDocument);
    //     } catch (MaxUploadSizeExceededException e) {
    //         return ResponseEntity.status(413).build(); // 413 Request Entity Too Large
    //     } catch (IOException e) {
    //         return ResponseEntity.status(500).build(); // Internal Server Error
    //     }
    // }