// package com.it332.edudeck.Service;

// import com.google.cloud.vertexai.VertexAI;
// import com.google.cloud.vertexai.generativeai.GenerativeModel;
// import com.google.cloud.vertexai.generativeai.ContentMaker;
// import com.google.cloud.vertexai.generativeai.PartMaker;
// import com.google.cloud.vertexai.generativeai.api.GenerateContentResponse;
// import com.google.cloud.vertexai.generativeai.api.ResponseStream;
// import org.springframework.stereotype.Service;

// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.List;

// @Service
// public class VertexAIService {

//     private static final String PROJECT_ID = "your-project-id";
//     private static final String LOCATION = "us-central1";

//     public List<String> generateFlashcards(String extractedText) throws IOException {
//         List<String> flashcards = new ArrayList<>();

//         try (VertexAI vertexAi = new VertexAI(PROJECT_ID, LOCATION)) {
//             GenerativeModel model = new GenerativeModel("gemini-pro-vision", vertexAi);

//             ResponseStream<GenerateContentResponse> stream = model.generateContentStream(
//                     ContentMaker.fromMultiModalData(
//                             extractedText,
//                             PartMaker.fromMimeTypeAndData("text/plain", extractedText)
//                     )
//             );

//             for (GenerateContentResponse response : stream) {
//                 // Assuming the response contains the flashcards in a JSON format
//                 // Parse the response to extract flashcard questions and answers
//                 // This is a placeholder; update with actual response parsing
//                 flashcards.add("Q: Example question? A: Example answer.");
//             }
//         }

//         return flashcards;
//     }
// }
