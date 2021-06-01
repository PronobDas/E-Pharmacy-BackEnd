package com.example.restservice.repository;

import com.example.restservice.models.doctor.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends MongoRepository<Doctor, String> {
    Optional<Doctor> findByDoctorId(String doctorId);
    List<Doctor> findByDoctorNameContaining(String doctorName);

}
