package com.example.restservice.models.doctor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "doctors")
public class Doctor {
    @Id
    private String doctorId;
    private String hospitalId;
    private String doctorName;
    private String speciality;
    private String address;
    private String about;
    //private String profilePicture;
    private String createdOn;

    public Doctor(String doctorId, String hospitalId, String doctorName, String speciality, String address, String about, String createdOn) {
        this.doctorId = doctorId;
        this.hospitalId = hospitalId;
        this.doctorName = doctorName;
        this.speciality = speciality;
        this.address = address;
        this.about = about;
        this.createdOn = createdOn;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId='" + doctorId + '\'' +
                ", hospitalId='" + hospitalId + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", speciality='" + speciality + '\'' +
                ", address='" + address + '\'' +
                ", about='" + about + '\'' +
                ", createdOn=" + createdOn +
                '}';
    }
}
