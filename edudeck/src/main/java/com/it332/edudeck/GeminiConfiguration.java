package com.it332.edudeck;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.generativeai.GenerativeModel;

@Configuration(proxyBeanMethods = false)
public class GeminiConfiguration {

    @Bean
    public VertexAI vertexai() throws IOException{
        return new VertexAI("savvy-depot-423506-j9","us-central1");
    }

    @Bean
    public GenerativeModel generativeModel(VertexAI vertexAI){
        return new GenerativeModel("gemini-1.0-pro", vertexAI);
    }

}
