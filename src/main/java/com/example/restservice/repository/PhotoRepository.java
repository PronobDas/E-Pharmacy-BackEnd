package com.example.restservice.repository;

import com.example.restservice.models.photo.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PhotoRepository extends MongoRepository<Photo, String> {
}
