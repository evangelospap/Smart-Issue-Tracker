package com.smartissuetracker.backend.model;
/**
 * Represents the response from the AI summary generation service.
 */
public class AISummaryResponse {
    private String summary;
    private String aiSuggestion;

    public AISummaryResponse(String summary, String aiSuggestion) {
        this.summary = summary;
        this.aiSuggestion = aiSuggestion;
    }

    public String getSummary() { return summary; }
    public String getaiSuggestion() { return aiSuggestion; }
}
