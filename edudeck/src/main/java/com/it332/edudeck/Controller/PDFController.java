package com.it332.edudeck.Controller;

// package com.acadzen.acadzen.Controller;

// import java.util.Map;
// import java.util.NoSuchElementException;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import com.acadzen.acadzen.Entity.DocumentEntity;
// import com.acadzen.acadzen.Service.PDFService;

// @RestController
// @RequestMapping("/api/pdf")
// @CrossOrigin(origins = "http://localhost:3000")
// public class PDFController {

//     @Autowired
//     PDFService pdfService;

//     @GetMapping("/view/{documentID}")
//     public ResponseEntity<byte[]> viewPDF(@PathVariable int documentID) {
//         try {
//             byte[] content = pdfService.getPDFContent(documentID);
//             return ResponseEntity.ok()
//                     .header("Content-Type", "application/pdf")
//                     .body(content);
//         } catch (NoSuchElementException e) {
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//         }
//     }

//     @PostMapping("/highlight/{documentID}")
//     public ResponseEntity<DocumentEntity> highlightPDF(@PathVariable int documentID, @RequestBody Map<String, String> highlights) {
//         try {
//             DocumentEntity highlightedDocument = pdfService.highlightPDF(documentID, highlights);
//             return ResponseEntity.ok(highlightedDocument);
//         } catch (NoSuchElementException e) {
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//         }
//     }
// }

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.it332.edudeck.Entity.DocumentEntity;
import com.it332.edudeck.Service.PDFDocumentService;

@RestController
@RequestMapping("/api/pdf")
public class PDFController {

    @Autowired
    PDFDocumentService pdfService;

        @GetMapping("/view/{userid}/{documentID}")
    public ResponseEntity<byte[]> viewDocument(@PathVariable int userid, @PathVariable int documentID) {
        try {
            DocumentEntity document = pdfService.getDocumentByIdAndUserId(documentID, userid);
            if (document == null || document.getIsDeleted()) {
                return ResponseEntity.status(404).build();
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(document.getFileType()))
                    .body(document.getFileContent());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(404).build();
        }
    }
}

