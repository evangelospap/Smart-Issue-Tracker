package com.smartissuetracker.backend.service;

import org.springframework.stereotype.Service;

import com.smartissuetracker.backend.model.Issue;
import com.smartissuetracker.backend.repository.IssueRepository;

import java.util.List;

@Service
public class IssueService {

    private final IssueRepository issueRepository;

    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public List<Issue> getAllIssues() {
        return issueRepository.findAll();
    }

    public Issue getIssueById(Long id) {
        return issueRepository.findById(id).orElseThrow();
    }

    public Issue createIssue(Issue issue) {
        return issueRepository.save(issue);
    }
}
