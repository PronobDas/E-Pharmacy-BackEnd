package com.example.restservice.models.medicine;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "medicines")
public class Medicine {
    @Id
    private String id;

    private String name;
    private String genericName;
    private String companyName;
    private double weight;
    private double unitPrice;
    private String sensitivity;
    private String imageURL;

    public Medicine(String name, String genericName, String companyName, double weight, double unitPrice, String sensitivity, String imageURL) {
        this.name = name;
        this.genericName = genericName;
        this.companyName = companyName;
        this.weight = weight;
        this.unitPrice = unitPrice;
        this.sensitivity = sensitivity;
        this.imageURL = imageURL;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getSensitivity() {
        return sensitivity;
    }

    public void setSensitivity(String sensitivity) {
        this.sensitivity = sensitivity;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", genericName='" + genericName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", weight=" + weight +
                ", unitPrice=" + unitPrice +
                ", sensitivity='" + sensitivity + '\'' +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}
