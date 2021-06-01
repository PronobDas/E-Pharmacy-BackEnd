package com.example.restservice.models.patient;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "patients")
public class Patient {
    private String regId;
    private String doctorId;
    private String hospitalId;
    @Id
    private String upi; // Universal Patient Identifier
    private String patientName;
    private String regDatetime;
    private String dateOfBirth;
    private Long age;
    private String gender;
    private String occupation;
    private String healthInsuranceNo;
    private String healthCareProvider;
    private String address;
    private String contactNo;
    private String createdOn;

    public Patient(String regId, String doctorId, String hospitalId, String upi, String patientName, String regDatetime, String dateOfBirth, Long age, String gender, String occupation, String healthInsuranceNo, String healthCareProvider, String address, String contactNo, String createdOn) {
        this.regId = regId;
        this.doctorId = doctorId;
        this.hospitalId = hospitalId;
        this.upi = upi;
        this.patientName = patientName;
        this.regDatetime = regDatetime;
        this.dateOfBirth = dateOfBirth;
        this.age = age;
        this.gender = gender;
        this.occupation = occupation;
        this.healthInsuranceNo = healthInsuranceNo;
        this.healthCareProvider = healthCareProvider;
        this.address = address;
        this.contactNo = contactNo;
        this.createdOn = createdOn;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
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

    public String getUpi() {
        return upi;
    }

    public void setUpi(String upi) {
        this.upi = upi;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getRegDatetime() {
        return regDatetime;
    }

    public void setRegDatetime(String regDatetime) {
        this.regDatetime = regDatetime;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getHealthInsuranceNo() {
        return healthInsuranceNo;
    }

    public void setHealthInsuranceNo(String healthInsuranceNo) {
        this.healthInsuranceNo = healthInsuranceNo;
    }

    public String getHealthCareProvider() {
        return healthCareProvider;
    }

    public void setHealthCareProvider(String healthCareProvider) {
        this.healthCareProvider = healthCareProvider;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "regId='" + regId + '\'' +
                ", doctorId='" + doctorId + '\'' +
                ", hospitalId='" + hospitalId + '\'' +
                ", upi='" + upi + '\'' +
                ", patientName='" + patientName + '\'' +
                ", regDatetime=" + regDatetime +
                ", dateOfBirth=" + dateOfBirth +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", occupation='" + occupation + '\'' +
                ", healthInsuranceNo='" + healthInsuranceNo + '\'' +
                ", healthCareProvider='" + healthCareProvider + '\'' +
                ", address='" + address + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", createdOn=" + createdOn +
                '}';
    }
}
