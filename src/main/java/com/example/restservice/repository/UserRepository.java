package com.example.restservice.repository;

import com.example.restservice.models.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findById(String id);
    Optional<User> findByEmail(String email);
    List<User> findByFirstNameContaining(String name);
}
