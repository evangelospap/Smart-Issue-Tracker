package com.smartissuetracker.backend.model;
/**
 * Represents a request for AI summary generation.
 */
public class AISummaryRequest {
    private String title;
    private String summary; // or full description

    // getters/setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }
}
