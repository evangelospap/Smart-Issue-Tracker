package com.smartissuetracker.backend.ai;

public interface AiClient {
    /**
     * Generates a summary and suggested fix for the given title and description.
     * This is a placeholder for actual AI service integration.
     * @param title
     * @param description
     * @return AiSummaryResponse containing the summary and suggestion
     */
  AiSummaryResponse summarizeAndSuggest(String title, String description);
}