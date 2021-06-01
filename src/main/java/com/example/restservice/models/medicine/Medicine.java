package com.example.restservice.models.medicine;

import org.springframework.data.annotation.Id;

public class Medicine {
    @Id
    String id;

    String name;
    String genericName;
    String manufacturerId;
    double weight;
    double unitPrice;
    String sensitivity;

}
