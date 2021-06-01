package com.example.restservice.repository;

import com.example.restservice.models.report.Medicine;
import com.example.restservice.models.report.Report;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MedicineRepository extends MongoRepository<Medicine, String> {
}
