package com.java.data_processing_engine.service.repository;

import com.java.data_processing_engine.model.ProcessingResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcessingResultRepository
        extends JpaRepository<ProcessingResult, Long> {
}
