package com.smartissuetracker.backend.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode()
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(length = 20)
    private String aiStatus = "PENDING"; // PENDING | READY | ERROR
    @Column(columnDefinition = "TEXT")
    private String aiSummary;
    @Column(columnDefinition = "TEXT")
    private String aiSuggestion;

    private String assignee;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
