package com.example.restservice.models.order;

import com.example.restservice.models.medicine.Medicine;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "orders")
public class Order {
    @Id
    private String id;

    private String location;
    private double totalPrice;
    private String prescription;

    private List<Integer> units;
    private List<Medicine> medicines;

    public Order(){}

    public Order(String location, double totalPrice, String prescription, List<Integer> units, List<Medicine> medicines) {
        this.location = location;
        this.prescription = prescription;
        this.units = units;
        this.medicines = medicines;
        this.totalPrice = this.calculateTotalPrice();
    }

    public double calculateTotalPrice()
    {
        double tp = 0;
        try {
            for (int i = 0; i < medicines.size(); i++) {
                tp += medicines.get(i).getUnitPrice() * units.get(i);
            }
            return tp;
        }
        catch (Exception e) {
            return 0.0;
        }
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

    public List<Integer> getUnits() {
        return units;
    }

    public void setUnits(List<Integer> units) {
        this.units = units;
    }

    public void setUnit(int unit) {
        this.units.add(unit);
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }

    public void setMedicine(Medicine medicine)
    {
        this.medicines.add(medicine);
    }
}