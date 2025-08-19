package com.smartissuetracker.backend.service;


import com.smartissuetracker.backend.ai.AiClient;
import com.smartissuetracker.backend.ai.AiProviderRouter;
import com.smartissuetracker.backend.ai.AiSummaryResponse;
import com.smartissuetracker.backend.model.Issue;
import com.smartissuetracker.backend.repository.IssueRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AiService {
  private static final Logger log = LoggerFactory.getLogger(AiService.class);

  private final IssueRepository issueRepository;
  private final AiProviderRouter router; // picks the right client

  @Async
  @Transactional
  public void enrichIssueAsync(Long issueId) {
    Issue issue = issueRepository.findById(issueId).orElse(null);
    if (issue == null) {
      log.warn("Issue {} not found, skipping AI enrichment", issueId);
      return;
    }
    try {
      issue.setAiStatus("PENDING");
      issueRepository.save(issue);

      AiClient client = router.client();
      AiSummaryResponse result = client.summarizeAndSuggest(issue.getTitle(), issue.getDescription());

      if (result.getSummary() != null || result.getAiSuggestion() != null) {
        issue.setAiSummary(result.getSummary());
        issue.setAiSuggestion(result.getAiSuggestion());
        issue.setAiStatus("READY");
      } else {
        issue.setAiStatus("ERROR");
        log.warn("AI returned empty result for issue {}", issueId);
      }

    } catch (Exception e) {
      issue.setAiStatus("ERROR");
      log.error("Failed to enrich issue {} with AI", issueId, e);
    } finally {
      issueRepository.save(issue); // persist final state
    }
  }
}
