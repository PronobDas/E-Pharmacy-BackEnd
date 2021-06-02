package com.example.restservice.repository;

import com.example.restservice.models.vendor.Vendor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VendorRepository extends MongoRepository<Vendor, String> {
    List<Vendor> findByLocationId(String locationId);
}
