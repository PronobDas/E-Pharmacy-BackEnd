package com.example.restservice.repository;

import com.example.restservice.models.order.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OrderRepository extends MongoRepository<Order, String> {
    Optional<Order> findByUserId(String userId);
}
