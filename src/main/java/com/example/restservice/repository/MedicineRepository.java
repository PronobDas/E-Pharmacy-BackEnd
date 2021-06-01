package com.example.restservice.repository;

import com.example.restservice.models.medicine.Medicine;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface MedicineRepository extends MongoRepository<Medicine, String> {
    List<Medicine> findByNameContaining( String name );
    List<Medicine> findByGenericNameContaining( String genericName );
}