// src/main/java/com/smartissuetracker/backend/ai/ClaudeClient.java
package com.smartissuetracker.backend.ai;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class ClaudeClient implements AiClient {
  private final WebClient webClient;
  private final String model;
  private final ObjectMapper mapper = new ObjectMapper();

  public ClaudeClient(
      @Value("${ai.claude.base-url}") String baseUrl,
      @Value("${ai.claude.api-key}") String apiKey,
      @Value("${ai.claude.model}") String model
  ) {
    this.model = model;
    this.webClient = WebClient.builder()
        .baseUrl(baseUrl)
        .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build();
  }

  @Override
  public AiSummaryResponse summarizeAndSuggest(String title, String description) {
    String system = "You are an expert triage assistant for a software issue tracker. " +
        "Given an issue title and description, produce: 1) a crisp 1-2 sentence summary, " +
        "2) a concrete suggested fix (code-level hints, steps, or owners). " +
        "Return JSON with keys 'summary' and 'suggestion'.";

    String user = "TITLE: " + title + "\n\nDESCRIPTION: " + description;

    Map<String, Object> payload = Map.of(
        "model", model,
        "input", List.of(
            Map.of("role", "system", "content", system),
            Map.of("role", "user", "content", user)
        ),
        "temperature", 0.2
    );

    try {
      Map<?, ?> response = this.webClient.post()
          .uri("/v1/complete") // adjust if Claude’s endpoint differs
          .body(BodyInserters.fromValue(payload))
          .retrieve()
          .bodyToMono(Map.class)
          .block();

      if (response == null || !response.containsKey("completion")) {
        log.error("Claude returned no completion: {}", response);
        return new AiSummaryResponse(null, null);
      }

      String content = (String) response.get("completion");

      JsonNode node = mapper.readTree(content);
      String summary = node.has("summary") ? node.get("summary").asText() : null;
      String suggestion = node.has("suggestion") ? node.get("suggestion").asText() : null;

      return new AiSummaryResponse(summary, suggestion);

    } catch (Exception e) {
      log.error("Failed to call Claude API", e);
      return new AiSummaryResponse(null, null);
    }
  }
}
