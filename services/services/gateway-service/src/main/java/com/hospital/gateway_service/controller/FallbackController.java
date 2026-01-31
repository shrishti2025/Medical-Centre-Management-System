package com.hospital.gateway_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.Map;


@RequestMapping("/fallback")
@RestController
public class FallbackController {
    
    @GetMapping("/admin")
    public Map<String, Object> adminFallback() {
        return Map.of(
            "success", false,
            "message", "Admin service temporarily unavailable",
            "timestamp", LocalDateTime.now()
        );
    }
    
    @GetMapping("/patient")
    public Map<String, Object> patientFallback() {
        return Map.of(
            "success", false,
            "message", "Patient service temporarily unavailable",
            "timestamp", LocalDateTime.now()
        );
    }
}
