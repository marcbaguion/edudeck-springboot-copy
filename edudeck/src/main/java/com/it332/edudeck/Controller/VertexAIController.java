// package com.it332.edudeck.Controller;

// import com.it332.edudeck.Service.VertexAIService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;

// import java.io.IOException;
// import java.util.List;

// @RestController
// @RequestMapping("/api/ai")
// public class VertexAIController {

//     @Autowired
//     private VertexAIService vertexAIService;

//     @PostMapping("/generateFlashcards")
//     public List<String> generateFlashcards(@RequestBody String extractedText) throws IOException {
//         return vertexAIService.generateFlashcards(extractedText);
//     }
// }
