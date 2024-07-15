// package com.it332.edudeck.Controller;

// import java.io.IOException;
// import java.util.List;
// import java.util.NoSuchElementException;
// import java.util.stream.Collectors;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ContentDisposition;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RequestPart;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.multipart.MaxUploadSizeExceededException;
// import org.springframework.web.multipart.MultipartFile;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.it332.edudeck.Entity.DocumentEntity;
// import com.it332.edudeck.Entity.UserEntity;
// import com.it332.edudeck.Service.DocumentService;
// import com.it332.edudeck.Service.UserService;

// @RestController
// @CrossOrigin(origins = "http://localhost:3000")
// @RequestMapping("/api/document")
// public class UploadDocumentController {

//     @Autowired
//     private DocumentService dserv;

//     @Autowired
//     private UserService userv;

//     @PostMapping("/upload/{userid}")
//     public ResponseEntity<DocumentEntity> insertDocument(
//             @RequestPart("document") String documentJson,
//             @RequestPart("file") MultipartFile file,
//             @PathVariable int userid) throws IOException {
//         try {
//             // Convert JSON string to DocumentEntity
//             ObjectMapper mapper = new ObjectMapper();
//             DocumentEntity document = mapper.readValue(documentJson, DocumentEntity.class);

//             UserEntity user = userv.findUserById(userid); // Use userService to find user by ID
//             DocumentEntity savedDocument = dserv.insertDocument(document, file, user);
//             return ResponseEntity.ok(savedDocument);
//         } catch (MaxUploadSizeExceededException e) {
//             // Handle file size exceeded error
//             return ResponseEntity.status(413).build(); // 413 Request Entity Too Large
//         } catch (NoSuchElementException e) {
//             // Handle user not found error
//             return ResponseEntity.status(404).body(null); // 404 Not Found
//         }
//     }

//     @GetMapping("/files/{userid}")
//     public ResponseEntity<List<DocumentEntity>> getDocumentsByUser(@PathVariable int userid) {
//         UserEntity user = userv.findUserById(userid); // Use userService to find user by ID
//         List<DocumentEntity> uploadedFiles = dserv.getDocumentsByUser(user);
//         return ResponseEntity.ok(uploadedFiles);
//     }

//     @PutMapping("/update/{documentID}")
//     public ResponseEntity<DocumentEntity> updateDocument(@PathVariable int documentID, @RequestParam(name = "newFileName", required = false) String newFileName) {
//         DocumentEntity updatedDocument = dserv.updateDocument(documentID, newFileName);
//         return ResponseEntity.ok(updatedDocument);
//     }

//     @DeleteMapping("/delete/{documentID}")
//     public ResponseEntity<DocumentEntity> deleteDocument(@PathVariable int documentID) {
//         DocumentEntity softDeletedDocument = dserv.deleteDocument(documentID);
//         return ResponseEntity.ok(softDeletedDocument);
//     }
// ////// added this code
//     @GetMapping("/files/contentByName/{fileName}")
//     public ResponseEntity<byte[]> getFileContentByFileName(@PathVariable String fileName) {
//         try {
//             byte[] fileContent = dserv.getFileContentByFileName(fileName);

//             // Send the file content back as the response
//             HttpHeaders headers = new HttpHeaders();
//             headers.setContentType(MediaType.APPLICATION_PDF); // Set the appropriate content type
//             headers.setContentDisposition(ContentDisposition.builder("inline").build()); // Set content disposition

//             return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
//         } catch (IOException e) {
//             // Handle IO errors
//             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//         } catch (NoSuchElementException e) {
//             // Handle file not found errors
//             return ResponseEntity.notFound().build();
//         }
//     }


// }


package com.it332.edudeck.Controller;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.it332.edudeck.Entity.Document;
import com.it332.edudeck.Entity.User;
import com.it332.edudeck.Service.DocumentService;
import com.it332.edudeck.Service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/document")
public class UploadDocumentController {

    @Autowired
    private DocumentService dserv;

    @Autowired
    private UserService userv;

    @PostMapping("/upload/{userid}")
    public ResponseEntity<Document> insertDocument(
            @RequestPart("document") String documentJson,
            @RequestPart("file") MultipartFile file,
            @PathVariable int userid) throws IOException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Document document = mapper.readValue(documentJson, Document.class);

            User user = userv.findUserById(userid);
            Document savedDocument = dserv.insertDocument(document, file, user);
            return ResponseEntity.ok(savedDocument);
        } catch (MaxUploadSizeExceededException e) {
            return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/files/{userid}")
    public ResponseEntity<List<Document>> getDocumentsByUser(@PathVariable int userid) {
        try {
            User user = userv.findUserById(userid);
            List<Document> uploadedFiles = dserv.getDocumentsByUser(user);
            return ResponseEntity.ok(uploadedFiles);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/update/{documentID}")
    public ResponseEntity<Document> updateDocument(@PathVariable int documentID, @RequestParam(name = "newFileName", required = false) String newFileName) {
        try {
            Document updatedDocument = dserv.updateDocument(documentID, newFileName);
            return ResponseEntity.ok(updatedDocument);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/delete/{documentID}")
    public ResponseEntity<Document> deleteDocument(@PathVariable int documentID) {
        try {
            Document softDeletedDocument = dserv.deleteDocument(documentID);
            return ResponseEntity.ok(softDeletedDocument);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/files/content/{documentID}")
    public ResponseEntity<byte[]> getFileContentByDocumentID(@PathVariable int documentID) {
        try {
            Document document = dserv.getDocumentById(documentID);
            byte[] fileContent = dserv.getFileContentByDocumentID(documentID);
            HttpHeaders headers = new HttpHeaders();

            // Determine file type and set appropriate headers
            switch (document.getFileType().toLowerCase()) {
                case "pdf":
                    headers.setContentType(MediaType.APPLICATION_PDF);
                    break;
                case "docx":
                    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                    headers.setContentDisposition(ContentDisposition.builder("inline").filename("document.docx").build());
                    break;
                case "pptx":
                    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                    headers.setContentDisposition(ContentDisposition.builder("inline").filename("presentation.pptx").build());
                    break;
                case "jpeg":
                case "jpg":
                    headers.setContentType(MediaType.IMAGE_JPEG);
                    headers.setContentDisposition(ContentDisposition.builder("inline").filename("image.jpg").build());
                    break;
                case "png":
                    headers.setContentType(MediaType.IMAGE_PNG);
                    headers.setContentDisposition(ContentDisposition.builder("inline").filename("image.png").build());
                    break;
                default:
                    return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(null);
            }

            return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
