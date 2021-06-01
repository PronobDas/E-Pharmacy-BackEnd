package com.example.restservice.repository;

import com.example.restservice.models.doctor.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends MongoRepository<Doctor, String> {
    Optional<Doctor> findById(String id);
    List<Doctor> findByNameContaining(String name);
    List<Doctor> findBySpecialityContaining(String speciality);
    List<Doctor> findByLocationId(String locationId);
    List<Doctor> findByDesignationContaining(String designation);



}
