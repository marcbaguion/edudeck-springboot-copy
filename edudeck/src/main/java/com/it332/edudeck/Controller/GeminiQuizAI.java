package com.it332.edudeck.Controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import com.it332.edudeck.Entity.Quiz;
import com.it332.edudeck.Entity.QuizItem;
import com.it332.edudeck.Repository.QuizRepository;
import com.it332.edudeck.Repository.QuizItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Iterator;

@RestController
public class GeminiQuizAI {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuizItemRepository quizItemRepository;

    @PostMapping("/generate-quiz")
    public String generateQuizItems(@RequestBody String lessonText, @RequestParam int quizId, @RequestParam String difficultyLevel, @RequestParam int numQuestions) {
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

            String systemInstructionText = "You are to create " + numQuestions + " quiz questions (question and answer, in json format if possible) " +
                "based on the given lesson texts. The questions should be of '" + difficultyLevel + "' difficulty level to help the student user review " +
                "and ace their exams. Ensure that there are a variety of questions: at least 3 true/false, at least 2 multiple choice(include the choices in the question the user can input the letter of the correct answer) " +
                "For language learning materials, provide choices with Japanese characters where necessary. The focus should be on helping the student " +
                "familiarize themselves with key concepts and terms from the lesson.";


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

            // Collect and concatenate the text parts
            String result = responseStream.stream()
                    .flatMap(response -> response.getCandidatesList().stream())
                    .flatMap(candidate -> candidate.getContent().getPartsList().stream())
                    .map(Part::getText)
                    .collect(Collectors.joining());

            // Remove enclosing backticks and trim whitespace
            result = result.replaceAll("```json|```", "").trim();

            // Save the quiz items to the database
            saveQuizItems(result, quizId);

            return "Quiz items generated and saved successfully.";
        } catch (IOException e) {
            e.printStackTrace();
            return "Error occurred: " + e.getMessage();
        } finally {
            if (vertexAi != null)
                vertexAi.close();
        }
    }

    private void saveQuizItems(String jsonResponse, int quizId) throws IOException {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode quizItemsNode = objectMapper.readTree(jsonResponse);

        for (Iterator<JsonNode> it = quizItemsNode.elements(); it.hasNext(); ) {
            JsonNode quizItemNode = it.next();
            String question = quizItemNode.get("question").asText();
            String answer = quizItemNode.get("answer").asText();

            QuizItem quizItem = new QuizItem();
            quizItem.setQuestion(question);
            quizItem.setCorrectAnswer(answer);
            quizItem.setQuiz(quiz);

            quizItemRepository.save(quizItem);
        }
    }
}
