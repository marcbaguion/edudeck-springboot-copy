package com.it332.edudeck.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.ResponseHandler;

import java.io.IOException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/gemini-pro")
public class GeminiProController {

    private final GenerativeModel generativeModel;

    public GeminiProController(GenerativeModel generativeModel) {
        this.generativeModel = generativeModel;
    }

    @PostMapping
    public String prompt(@RequestBody String question) throws IOException {
        GenerateContentResponse generateContentResponse= this.generativeModel.generateContent(question);
        
        return ResponseHandler.getText(generateContentResponse);
    }
    
}
