package com.example.restservice.repository;

import com.example.restservice.models.location.Location;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface LocationRepository extends MongoRepository<Location, String> {
    Optional<Location> findByLongitudeAndLatitude(double longitude, double latitude);
}
