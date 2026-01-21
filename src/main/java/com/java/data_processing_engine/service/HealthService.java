package com.java.data_processing_engine.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HealthService {

    public Map<String, Object> processData(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            return Map.of("error", "No data provided");
        }

        int sum = numbers.stream().mapToInt(Integer::intValue).sum();
        double average = numbers.stream().mapToInt(Integer::intValue).average().orElse(0.0);
        int max = numbers.stream().mapToInt(Integer::intValue).max().orElse(0);
        int min = numbers.stream().mapToInt(Integer::intValue).min().orElse(0);

        return Map.of(
            "sum", sum,
            "average", average,
            "max", max,
            "min", min,
            "count", numbers.size()
        );
    }
}
