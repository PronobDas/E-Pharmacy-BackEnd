package com.example.restservice.models.order;

import com.example.restservice.models.medicine.Medicine;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "orders")
public class Order {
    @Id
    private String id;

    private String userId;
    private String phone;
    private String location;
    private double totalPrice;
    private Binary prescription;
    private Boolean confirmed; // defines whether this order is confirmed or not

    private List<Integer> units;
    private List<Medicine> medicines;

    public Order(String userId, String phone, String location, double totalPrice, List<Integer> units, List<Medicine> medicines) {
        this.userId = userId;
        this.phone = phone;
        this.location = location;
        this.prescription = null;
        this.confirmed = false;
        this.units = units;
        this.medicines = medicines;
        this.totalPrice = this.calculateTotalPrice();
    }

    public Order(){}

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Binary getPrescription() {
        return prescription;
    }

    public void setPrescription(Binary prescription) {
        this.prescription = prescription;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
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

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", phone='" + phone + '\'' +
                ", location='" + location + '\'' +
                ", totalPrice=" + totalPrice +
                ", prescription=" + prescription +
                ", confirmed=" + confirmed +
                ", units=" + units +
                ", medicines=" + medicines +
                '}';
    }
}