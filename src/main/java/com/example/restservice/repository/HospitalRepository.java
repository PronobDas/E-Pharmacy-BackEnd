package com.example.restservice.repository;

import com.example.restservice.models.hospital.Hospital;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface HospitalRepository extends MongoRepository<Hospital, String> {
    Optional<Hospital> findByHospitalId(String hospitalId);
    List<Hospital> findByHospitalNameContaining(String hospitalName);
}
