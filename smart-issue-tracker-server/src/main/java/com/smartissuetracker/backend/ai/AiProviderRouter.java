package com.smartissuetracker.backend.ai;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AiProviderRouter {

    private final OpenAiClient openAiClient;
    private final ClaudeClient claudeClient;

    @Value("${ai.provider:openai}") // configurable in application.yml
    private String provider;

    public AiClient client() {
        if ("claude".equalsIgnoreCase(provider)) {
            return claudeClient;
        }
        return openAiClient; // default
    }
}
