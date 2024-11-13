package com.example.journal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {
    
    @GetMapping("journal/health-check")
    public String healthCheck() {
        return "OK";
    }
}
