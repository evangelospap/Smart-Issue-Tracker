package com.smartissuetracker.backend.ai;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Represents the response from the AI summary generation service.
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode()
public class AiSummaryResponse {
    private String summary;
    private String aiSuggestion;
}
