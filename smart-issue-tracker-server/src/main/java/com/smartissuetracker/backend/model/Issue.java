package com.smartissuetracker.backend.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class Issue extends BaseEntity {

    private String title;
    private String description;
    private String status;
    private String userId;
    private String priority;
    private String assignee;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
