package com.example.restservice.models.order;

import com.example.restservice.models.medicine.Medicine;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;

@Document(collection = "orders")
public class Order {
    @Id
    private String id;

    private String location;
    private int[] quantity;
    private double totalPrice;
    private String prescription;

    private Medicine[] medicines;

    public Order(String location, int[] quantity, double totalPrice, String prescription, Medicine[] medicines) {
        this.location = location;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.prescription = prescription;
        this.medicines = medicines;
    }

    public String getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int[] getQuantity() {
        return quantity;
    }

    public void setQuantity(int[] quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public Medicine[] getMedicines() {
        return medicines;
    }

    public void setMedicines(Medicine[] medicines) {
        this.medicines = medicines;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", location='" + location + '\'' +
                ", quantity=" + Arrays.toString(quantity) +
                ", totalPrice=" + totalPrice +
                ", prescription='" + prescription + '\'' +
                ", medicines=" + Arrays.toString(medicines) +
                '}';
    }
}
