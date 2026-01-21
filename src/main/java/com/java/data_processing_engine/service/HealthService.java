package com.java.data_processing_engine.service;

import com.java.data_processing_engine.model.ProcessingResult;
import com.java.data_processing_engine.service.repository.ProcessingResultRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class HealthService {

    private final ProcessingResultRepository repository;

    public HealthService(ProcessingResultRepository repository) {
        this.repository = repository;
    }

    // ===============================
    // API-based data processing
    // ===============================
    public Map<String, Object> processData(List<Integer> numbers) {

        if (numbers == null || numbers.isEmpty()) {
            return Map.of("error", "No data provided");
        }

        int sum = numbers.stream().mapToInt(Integer::intValue).sum();
        double average = numbers.stream().mapToInt(Integer::intValue).average().orElse(0.0);
        int max = numbers.stream().mapToInt(Integer::intValue).max().orElse(0);
        int min = numbers.stream().mapToInt(Integer::intValue).min().orElse(0);
        int count = numbers.size();

        // Save to database
        ProcessingResult result =new ProcessingResult(sum, average, max, min, count, "PROCESSED");


        repository.save(result);

        return Map.of(
                "sum", sum,
                "average", average,
                "max", max,
                "min", min,
                "count", count,
                "saved", true
        );
    }

    // ===============================
    // CSV / batch processing
    // ===============================
    public Map<String, Object> processCsvFile() {

        List<Integer> numbers = new ArrayList<>();

        try {
            ClassPathResource resource = new ClassPathResource("numbers.csv");
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(resource.getInputStream()));

            String line;
            boolean isHeader = true;

            while ((line = reader.readLine()) != null) {

                if (isHeader) {
                    isHeader = false; // skip CSV header
                    continue;
                }

                String[] columns = line.split(",");
                int value = Integer.parseInt(columns[1].trim()); // value column
                numbers.add(value);
            }

            return processData(numbers);

        } catch (Exception e) {
            return Map.of("error", "Failed to read CSV file");
        }
    }

    // ===============================
    // Fetch history from database
    // ===============================
    public List<ProcessingResult> getHistory() {
        return repository.findAll();
    }
}
