package com.smartissuetracker.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartissuetracker.backend.service.ClerkService;

import java.io.IOException;
@RestController
@RequestMapping("/api/users")
public class UserController {
    private final ClerkService clerkService;

    public UserController(ClerkService clerkService) {
        this.clerkService = clerkService;
    }
    @GetMapping("/{userId}")
    public String getUserInfo(@PathVariable String userId) throws IOException {
        return clerkService.getUserInfo(userId);
    }
}