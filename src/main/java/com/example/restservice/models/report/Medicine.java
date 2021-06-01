package com.example.restservice.models.report;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "medicines")
public class Medicine {
    @Id
    private String id;
    private String drugName;
    private Double unit;
    private Double dosage;

    public Medicine(String drugName, Double unit, Double dosage) {
        this.drugName = drugName;
        this.unit = unit;
        this.dosage = dosage;
    }

    public Medicine() {

    }

    public String getId() { return id; }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public Double getUnit() {
        return unit;
    }

    public void setUnit(Double unit) {
        this.unit = unit;
    }

    public Double getDosage() {
        return dosage;
    }

    public void setDosage(Double dosage) {
        this.dosage = dosage;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "drugName='" + drugName + '\'' +
                ", unit=" + unit +
                ", dosage=" + dosage +
                '}';
    }
}