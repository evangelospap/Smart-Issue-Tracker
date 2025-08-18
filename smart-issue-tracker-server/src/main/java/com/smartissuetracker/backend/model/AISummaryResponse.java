package com.smartissuetracker.backend.model;
/**
 * Represents the response from the AI summary generation service.
 */
public class AISummaryResponse {
    private String summary;
    private String suggestedFix;

    public AISummaryResponse(String summary, String suggestedFix) {
        this.summary = summary;
        this.suggestedFix = suggestedFix;
    }

    public String getSummary() { return summary; }
    public String getSuggestedFix() { return suggestedFix; }
}
