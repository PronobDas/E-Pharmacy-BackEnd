package com.example.restservice.models.order;

import com.example.restservice.models.medicine.Medicine;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orders")
public class Order {
    @Id
    private String id;

    private String location;
    private int[] quantity;
    private double totalPrice;
    private String prescription;

    private Medicine[] medicines;



}
