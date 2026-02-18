package com.gym.dailygym.service;

import com.gym.dailygym.persistence.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;

    public UUID handleUpload(MultipartFile file) {

        // Validate extension
        if (!file.getOriginalFilename().toLowerCase().endsWith(".csv")) {
            throw new IllegalArgumentException("Only CSV files are allowed.");
        }

        // Validate size (< 50MB)
        if (file.getSize() > 50 * 1024 * 1024) {
            throw new IllegalArgumentException("File too large. Max 50MB.");
        }

        // Save file to disk
        Path uploadDir = Paths.get("uploads");
        try {
            Files.createDirectories(uploadDir);
            Path filePath = uploadDir.resolve(file.getOriginalFilename());
            Files.write(filePath, file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to save file.");
        }

        // Create job entry
        Job job = new Job();
        job.setStatus(JobStatus.UPLOADED);
        job.setCreatedAt(LocalDateTime.now());
        jobRepository.save(job);

        return job.getJobId();
    }
}
