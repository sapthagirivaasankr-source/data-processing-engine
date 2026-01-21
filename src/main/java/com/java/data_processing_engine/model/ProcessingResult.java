package com.java.data_processing_engine.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ProcessingResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int sum;
    private double average;
    private int max;
    private int min;
    private int count;
    private String status;

    public ProcessingResult() {}

    public ProcessingResult(int sum, double average, int max, int min, int count, String status) {
        this.sum = sum;
        this.average = average;
        this.max = max;
        this.min = min;
        this.count = count;
        this.status = status;
    }

    public Long getId() { return id; }
    public int getSum() { return sum; }
    public double getAverage() { return average; }
    public int getMax() { return max; }
    public int getMin() { return min; }
    public int getCount() { return count; }
    public String getStatus() { return status; }
}
