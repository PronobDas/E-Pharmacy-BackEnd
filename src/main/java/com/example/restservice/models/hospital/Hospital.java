package com.example.restservice.models.hospital;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "hospitals")
public class Hospital {
    @Id
    private String hospitalId;
    private String hospitalName;
    private String branchName;
    private String address;
    private String email;
    private String contact;
    private String createdOn;

    public Hospital(String hospitalId, String hospitalName, String branchName, String address, String email, String contact, String createdOn) {
        this.hospitalId = hospitalId;
        this.hospitalName = hospitalName;
        this.branchName = branchName;
        this.address = address;
        this.email = email;
        this.contact = contact;
        this.createdOn = createdOn;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        return "Hospital{" +
                "hospitalId='" + hospitalId + '\'' +
                ", hospitalName='" + hospitalName + '\'' +
                ", branchName='" + branchName + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", contact='" + contact + '\'' +
                ", createdOn=" + createdOn +
                '}';
    }
}
