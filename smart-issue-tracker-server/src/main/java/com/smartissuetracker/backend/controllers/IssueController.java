package com.smartissuetracker.backend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;

@RestController
public class IssueController {

    @GetMapping("/api/issues")
    public String getIssues(@AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getClaimAsString("sub"); // Clerk User ID
        return "Hello from backend! Authenticated user: " + userId;
    }
}
