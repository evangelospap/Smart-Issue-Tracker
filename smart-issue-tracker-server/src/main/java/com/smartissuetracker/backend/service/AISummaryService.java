package com.smartissuetracker.backend.service;

import org.springframework.stereotype.Service;

import com.smartissuetracker.backend.model.AISummaryResponse;

@Service
public class AISummaryService {
    /**
     * Generates a summary and suggested fix for the given title and description.
     * This is a placeholder for actual AI service integration.
     * @param title
     * @param description
     * @return
     */
    public AISummaryResponse generateSummary(String title, String description) {
        // Here you’d call OpenAI or any AI provider
        // For now, mock response
        String summary = "This issue seems related to login failure.";
        String suggestedFix = "Check OAuth credentials and session handling.";

        return new AISummaryResponse(summary, suggestedFix);
    }
}
