package com.it332.edudeck.Controller;

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.Blob;
import com.google.cloud.vertexai.api.Content;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.api.GenerationConfig;
import com.google.cloud.vertexai.api.HarmCategory;
import com.google.cloud.vertexai.api.Part;
import com.google.cloud.vertexai.api.SafetySetting;
import com.google.cloud.vertexai.generativeai.ContentMaker;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.PartMaker;
import com.google.cloud.vertexai.generativeai.ResponseStream;
import com.google.protobuf.ByteString;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeminiFlashcardAI  {
  public static void main(String[] args) throws IOException {
    try (VertexAI vertexAi = new VertexAI("flashcardai-424201", "us-central1"); ) {
      GenerationConfig generationConfig =
          GenerationConfig.newBuilder()
              .setMaxOutputTokens(8192)
              .setTemperature(1F)
              .setTopP(0.95F)
              .build();
      List<SafetySetting> safetySettings = Arrays.asList(
        SafetySetting.newBuilder()
            .setCategory(HarmCategory.HARM_CATEGORY_HATE_SPEECH)
            .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_ONLY_HIGH)
            .build(),
        SafetySetting.newBuilder()
            .setCategory(HarmCategory.HARM_CATEGORY_DANGEROUS_CONTENT)
            .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_ONLY_HIGH)
            .build(),
        SafetySetting.newBuilder()
            .setCategory(HarmCategory.HARM_CATEGORY_SEXUALLY_EXPLICIT)
            .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_ONLY_HIGH)
            .build(),
        SafetySetting.newBuilder()
            .setCategory(HarmCategory.HARM_CATEGORY_HARASSMENT)
            .setThreshold(SafetySetting.HarmBlockThreshold.BLOCK_ONLY_HIGH)
            .build()
      );
    //   var systemInstruction = ContentMaker.fromMultiModalData(textsi_1);
      GenerativeModel model =
        new GenerativeModel.Builder()
            .setModelName("gemini-1.5-flash-preview-0514")
            .setVertexAi(vertexAi)
            .setGenerationConfig(generationConfig)
            .setSafetySettings(safetySettings)
            //.setSystemInstruction(systemInstruction)
            .build();

      var text1 = "1 Who is Jose Rizal? Name: José Protacio Mercado Rizal Alonzo Y Realonda Born: June 19, 1861, Calamba. Died: Dec. 30, 1896, Manila His journey begins and ended with beautiful and heart broken memoir… Rizal's LoveAffairs 2 Who are the Women who Caught his Heart? 1. Segunda Katigbak 2. Leonor Valenzuela 3. Leonor Rivera 4. Consuelo Ortiga 5. O Sei San 6. Gertrude Beckett 7. Nellie Bousted 8. Suzanne Jacoby 9. Josephine Bracken Rizal's LoveAffairs 3 Segunda Katigbak Rizal was his puppy love. Unfortunately, his first love was engaged to be married to a town mate- Manuel Luz. Rizal's LoveAffairs 4 After his admiration for a short girl in the person of Segunda, then came .. Rizal's LoveAffairs 5 “ Mapupula ang kaniyang pisngi, may kahali-halinang ngiti, at para siyang ada, ang buong katauhan niya’y may di- maipaliwanag na bighani.” -Jose Rizal Rizal's LoveAffairs 6 A tall girl from Pagsanjan. Rizal send her love notes written in invisible ink, that could only be deciphered over the warmth of the lamp or candle. Leonor Valenzuela Rizal's LoveAffairs 7 He visited her on the eve of his departure to Spain and bade her a last goodbye. Rizal's LoveAffairs 8 Rizal's LoveAffairs 9 Leonor Rivera - his sweetheart for 11 years played the greatest influence in keeping him from falling in love with other women during his travel. Rizal's LoveAffairs 10 Unfortunately, Leonor’s mother disapproved of her daughter’s relationship with Rizal, who was then a known filibustero. Rizal's LoveAffairs 11 Leonor Rivera She hid from Leonor all letters sent to her sweetheart. Leonor believing that Rizal had already forgotten her, sadly consented her to marry the Englishman Henry Kipping, her mother’s choice. Rizal's LoveAffairs 12 Rizal's LoveAffairs 13 Consuelo Ortiga Y Rey The prettier of Don Pablo Ortiga’s daughters, fell in love with him. He dedicated to her A la Senorita C.O. y R., which became one of his best poems. Rizal's LoveAffairs 14 The Ortiga's residence in Madrid was frequented by Rizal and his compatriots. He probably fell in love with her and Consuelo apparently asked him for romantic verses. Rizal's LoveAffairs 15 He suddenly backed out before the relationship turned into a serious romance, because he wanted to remain loyal to Leonor Rivera and he did not want to destroy his friendship with Eduardo de Lete who was madly in love with Consuelo. Rizal's LoveAffairs 16 Rizal's LoveAffairs 17 O Sei San - a Japanese samurai’s daughter taught Rizal the Japanese art of painting known as su\u0002mie. She also helped Rizal improve his knowledge of Japanese language. Rizal's LoveAffairs 18 If Rizal was a man without a patriotic mission, he would have married this lovely and intelligent woman and lived a stable and happy life with her in Japan because Spanish legation there offered him a lucrative job. Rizal's LoveAffairs 19 Rizal's LoveAffairs 20 Gertrude Beckett While Rizal was in London annotating the Sucesos de las Islas Filipinas, he boarded in the house of the Beckett family, within walking distance of the British Museum. Rizal's LoveAffairs 21 A blue eye and buxom girl was the oldest of the three Beckett daughters. She fell in love with Rizal. Tottie helped him in his painting and sculpture. But Rizal suddenly left London for Paris to avoid Gertrude, who was seriously in love with him. Rizal's LoveAffairs 22 Before leaving London, he was able to finish the group carving of the Beckett sisters. He gave the group carving to Gertrude as a sign of their brief relationship. Rizal's LoveAffairs 23 “Siyaayisangdalagangmayasulna mgamata,mapupulangpisngiat buhokna kulay-kayumang i.” – JoseRizal Rizal's LoveAffairs 24 Nellie Boustead •Rizal having lost Leonor Rivera, entertained the thought of courting other ladies. •While a guest of the Boustead family at their residence in the resort city of Biarritz, he had befriended the two pretty daughters of his host, Eduardo Boustead. Rizal's LoveAffairs 25 Rizal used to fence with the sisters at the studio of Juan Luna. Antonio Luna, Juan’s brother and also a frequent visitor of the Boustead's, courted Nellie but shewas deeply infatuated with Rizal. Rizal's LoveAffairs 26 Nellie Bousted In a party held by Filipinos in Madrid, a drunken Antonio Luna uttered unsavory remarks against Nellie Boustead. This prompted Rizal to challenge Luna into a duel. Rizal's LoveAffairs 27 Fortunately, Luna apologized to Rizal, thus averting tragedy for the compatriots. Their love affair unfortunately did not end in marriage. Rizal's LoveAffairs 28 It failed because Rizal refused to be converted to the Protestant faith, as Nellie demanded and Nellie’s mother did not like a physician without enough paying clientele to be a son\u0002in-law. Rizal's LoveAffairs 29 The lovers, however, parted as good friends when Rizal left Europe. Rizal's LoveAffairs 30 Rizal's LoveAffairs 31 Suzanne Jacoby ▪In 1890, Rizal moved to Brussels because of the high cost of living in Paris. ▪In Brussels, he lived in the boarding house of the two Jacoby sisters. Rizal's LoveAffairs 32 In time, they fell deeply in love with each other. Suzanne cried when Rizal left Brussels and wrote him when he was in Madrid. Rizal's LoveAffairs 33 Josephine Bracken In the last days of February 1895, while still in Dapitan, Rizal met an 18-year old petite Irish girl, with bold blue eyes, brown hair and a happy disposition. Rizal's LoveAffairs 34 She was Josephine Bracken, the adopted daughter of George Taufer from Hong Kong, who came to Dapitan to seek Rizal for eye treatment. Rizal's LoveAffairs 35 Rizal was physically attracted to her. His loneliness and boredom must have taken the measure of him and what could be a better diversion that to fall in love again. Rizal's LoveAffairs 36 But the Rizal sisters suspected Josephine as an agent of the friars and they considered her as a threat to Rizal’s security. Rizal's LoveAffairs 37 Rizal asked Josephine to marry him, but she was not yet ready to make a decision due to her responsibility to the blind Taufer. Since Taufer’s blindness was untreatable, he left for Hong Kong on March 1895. Rizal's LoveAffairs 38 Josephine stayed with Rizal’s family in Manila. Upon her return to Dapitan, Rizal tried to arrange with Father Antonio Obach for their marriage. Rizal's LoveAffairs 39 However, the priest wanted a retraction as a precondition before marrying them. Rizal upon the advice of his family and friends and with Josephine’s consent took her as his wife even without the Church blessings. Rizal's LoveAffairs 40 Josephine later give birth prematurely to a stillborn baby, a result of some incidence, which might have shocked or frightened her. Rizal's LoveAffairs 41 Rizal's LoveAffairs 42 Reference: https://dimasalanglaonglaan.wordp ress.com/women-in-rizals-life/ https://bshmjoserizal.weebly.com/ our-hero-jose-rizal/chapter\u0002seven-women-of-rizal Rizal's LoveAffairs 43 Rizal's LoveAffairs 44";

      var content = ContentMaker.fromMultiModalData(text1);
      ResponseStream<GenerateContentResponse> responseStream = model.generateContentStream(content);

      // Do something with the response
      responseStream.stream().forEach(System.out::println);
    }
  }
}
