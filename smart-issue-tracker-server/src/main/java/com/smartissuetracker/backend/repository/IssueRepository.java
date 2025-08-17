package com.smartissuetracker.backend.repository;
import com.smartissuetracker.backend.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue, Long> {
}
