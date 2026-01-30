package com.hospital.medical_patient_service.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @PostMapping("/test")
    public String test(@RequestBody Map<String, Object> body) {
        return "POST working! Received: " + body;
    }
}
