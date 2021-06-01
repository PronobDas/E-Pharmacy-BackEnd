package com.example.restservice.models.doctor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "doctors")
public class Doctor {
    @Id
    private String id;

    private String name;
    private String designation;
    private String speciality;
    private String locationId;
    private String day;
    private String time;
    private String contactNo;

    public Doctor(String name, String designation, String speciality, String locationId, String day, String time, String contactNo) {
        this.name = name;
        this.designation = designation;
        this.speciality = speciality;
        this.locationId = locationId;
        this.day = day;
        this.time = time;
        this.contactNo = contactNo;
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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", designation='" + designation + '\'' +
                ", speciality='" + speciality + '\'' +
                ", locationId='" + locationId + '\'' +
                ", day='" + day + '\'' +
                ", time='" + time + '\'' +
                ", contactNo='" + contactNo + '\'' +
                '}';
    }
}
