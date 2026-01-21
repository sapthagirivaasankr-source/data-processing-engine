package com.java.data_processing_engine.controller;

import com.java.data_processing_engine.service.HealthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class HealthController {

    private final HealthService healthService;

    public HealthController(HealthService healthService) {
        this.healthService = healthService;
    }

    @GetMapping("/health")
    public String health() {
        return "Application is healthy";
    }

    @GetMapping("/process")
    public Map<String, Object> processData(
            @RequestParam(required = false) String numbers) {

        if (numbers == null || numbers.trim().isEmpty()) {
            return Map.of("error", "Please provide numbers in the format 10,20,30");
        }

        try {
            List<Integer> data = Arrays.stream(numbers.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            return healthService.processData(data);

        } catch (NumberFormatException e) {
            return Map.of("error", "Only numbers are allowed. Example: 10,20,30");
        }
    }

    @GetMapping("/process-file")
    public Map<String, Object> processFile() {
        return healthService.processCsvFile();
    }

    @GetMapping("/history")
    public List<?> history() {
        return healthService.getHistory();
    }
}
