package com.it332.edudeck.Controller;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.it332.edudeck.Service.DocumentService;
import com.it332.edudeck.Entity.Document;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class TextExtractorController {

    @Autowired
    private DocumentService documentService;

    @GetMapping("/textextractor/document/{documentid}")
    public ResponseEntity<String> extractTextFromDocument(@PathVariable int documentid) {
        try {
            Document document = documentService.getDocumentById(documentid);
            byte[] fileContent = document.getFileContent();
            String fileType = document.getFileType().toLowerCase();
            String extractedText = "";

            switch (fileType) {
                case "docx":
                    extractedText = extractTextFromDocx(fileContent);
                    break;
                case "pptx":
                    extractedText = extractTextFromPptx(fileContent);
                    break;
                case "pdf":
                    extractedText = extractTextFromPdf(fileContent);
                    break;
                default:
                    return ResponseEntity.badRequest().body("Unsupported file type: " + fileType);
            }

            // Enclose the extracted text in triple single quotes
            String enclosedText = "'";
            enclosedText += extractedText.replace("'", "\\'");
            enclosedText += "'";

            return ResponseEntity.ok(enclosedText);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error processing document: " + e.getMessage());
        }
    }

    private String extractTextFromDocx(byte[] fileContent) throws IOException {
        StringBuilder text = new StringBuilder();
        try (XWPFDocument doc = new XWPFDocument(new ByteArrayInputStream(fileContent))) {
            List<XWPFParagraph> paragraphs = doc.getParagraphs();
            for (XWPFParagraph paragraph : paragraphs) {
                text.append(paragraph.getText()).append("\n");
            }
        }
        return text.toString();
    }

    private String extractTextFromPptx(byte[] fileContent) throws IOException {
        StringBuilder text = new StringBuilder();
        try (XMLSlideShow ppt = new XMLSlideShow(new ByteArrayInputStream(fileContent))) {
            for (XSLFSlide slide : ppt.getSlides()) {
                slide.getShapes().forEach(shape -> {
                    if (shape instanceof org.apache.poi.xslf.usermodel.XSLFTextShape) {
                        org.apache.poi.xslf.usermodel.XSLFTextShape textShape = (org.apache.poi.xslf.usermodel.XSLFTextShape) shape;
                        text.append(textShape.getText()).append("\n");
                    }
                });
            }
        }
        return text.toString();
    }

    private String extractTextFromPdf(byte[] fileContent) throws IOException {
        StringBuilder text = new StringBuilder();
        try (PDDocument document = PDDocument.load(new ByteArrayInputStream(fileContent))) {
            PDFTextStripper pdfStripper = new PDFTextStripper();
            text.append(pdfStripper.getText(document));
        }
        return text.toString();
    }
}