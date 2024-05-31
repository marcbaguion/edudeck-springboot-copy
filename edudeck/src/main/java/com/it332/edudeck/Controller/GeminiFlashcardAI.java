package com.it332.edudeck.Controller;

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.Content;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.api.GenerationConfig;
import com.google.cloud.vertexai.api.HarmCategory;
import com.google.cloud.vertexai.api.Part;
import com.google.cloud.vertexai.api.SafetySetting;
import com.google.cloud.vertexai.generativeai.ContentMaker;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.ResponseStream;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GeminiFlashcardAI {

    @PostMapping("/generate-flashcards")
    public String generateFlashcards(@RequestBody String lessonText) {
        VertexAI vertexAi = null;
        try {
            vertexAi = new VertexAI("savvy-depot-423506-j9", "us-central1");

            GenerationConfig generationConfig =
                    GenerationConfig.newBuilder()
                            .setMaxOutputTokens(8192)
                            .setTemperature(1F)
                            .setTopP(0.95F)
                            .build();
            List<SafetySetting> safetySettings = Arrays.asList(
                    SafetySetting.newBuilder()
                            .setCategory(HarmCategory.HARM_CATEGORY_HATE_SPEECH)
                            .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE)
                            .build(),
                    SafetySetting.newBuilder()
                            .setCategory(HarmCategory.HARM_CATEGORY_DANGEROUS_CONTENT)
                            .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE)
                            .build(),
                    SafetySetting.newBuilder()
                            .setCategory(HarmCategory.HARM_CATEGORY_SEXUALLY_EXPLICIT)
                            .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE)
                            .build(),
                    SafetySetting.newBuilder()
                            .setCategory(HarmCategory.HARM_CATEGORY_HARASSMENT)
                            .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_MEDIUM_AND_ABOVE)
                            .build()
            );

            String systemInstructionText = "You are to create flashcard pairs (question and answer, in json format if possible) " +
                    "based on the given lesson texts to help the student user review and ace his/her exams. " +
                    "The questions may have blanks and the answer is supposed to be blanked out. " +
                    "There could be true or false. Focus on helping the student users familiarize, remember, " +
                    "and learn the important keywords, points, and people in the lesson.";

            Content systemInstruction = ContentMaker.fromMultiModalData(systemInstructionText);

            GenerativeModel model = new GenerativeModel.Builder()
                    .setModelName("gemini-1.5-flash-001")
                    .setVertexAi(vertexAi)
                    .setGenerationConfig(generationConfig)
                    .setSafetySettings(safetySettings)
                    .setSystemInstruction(systemInstruction)
                    .build();

            Content content = ContentMaker.fromMultiModalData(lessonText);
            ResponseStream<GenerateContentResponse> responseStream = model.generateContentStream(content);

            // Collect and concatenate the text parts, ensuring it starts and ends with square brackets
            String result = responseStream.stream()
                    .flatMap(response -> response.getCandidatesList().stream())
                    .flatMap(candidate -> candidate.getContent().getPartsList().stream())
                    .map(Part::getText)
                    .collect(Collectors.joining());

            // Remove enclosing backticks
            result = result.replaceAll("```json|```", "").trim();
            
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return "Error occurred: " + e.getMessage();
        } finally {
            if (vertexAi != null) {
                vertexAi.close();
            }
        }
    }
}
