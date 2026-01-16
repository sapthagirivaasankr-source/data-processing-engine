package com.java.data_processing_engine.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController; 
@RestController
public class HealthController {
    @GetMapping("/health")
    public String healthCheck() {
        return "Data Processing Engine is running.";
    }
}     