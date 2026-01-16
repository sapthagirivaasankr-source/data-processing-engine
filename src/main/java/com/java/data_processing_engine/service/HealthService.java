package com.java.data_processing_engine.service;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HealthService {

    public String processData(List<Integer> numbers) {

        int sum = 0;
        for (int num : numbers) {
            sum = sum + num;
        }

        return "Processed result (sum) = " + sum;
    }
}
