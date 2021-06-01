package com.example.restservice.models.report;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "reports")
public class Report {
    @Id
    private String id;
    private String patientId;
    private String doctorId;
    @CreatedDate
    private Date createdDate;
    private Double bloodPressure;
    private Double pulseRate;
    private Double weight;
    private List<String> allergies;
    private List<String> disabilities;
    private List<Medicine> medicines;
    private List<Diet> diets;
    private String patientHistory;
    private String followupDoctorId;

    public Report(String patientid, String doctorid, Double bloodpressure, Double pulserate,
                  Double weight, List<String> allergies, List<String> disabilities,
                  List<Medicine> medicines, List<Diet> diets, String patienthistory, String followupdoctorid) {
        this.patientId = patientid;
        this.doctorId = doctorid;
        this.bloodPressure = bloodpressure;
        this.pulseRate = pulserate;
        this.weight = weight;
        this.allergies = allergies;
        this.disabilities = disabilities;
        this.medicines = medicines;
        this.diets = diets;
        this.patientHistory = patienthistory;
        this.followupDoctorId = followupdoctorid;
    }

    public Report() {

    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Double getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(Double bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public Double getPulseRate() {
        return pulseRate;
    }

    public void setPulseRate(Double pulseRate) {
        this.pulseRate = pulseRate;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public List<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(List<String> allergies) {
        this.allergies = allergies;
    }

    public List<String> getDisabilities() {
        return disabilities;
    }

    public void setDisabilities(List<String> disabilities) {
        this.disabilities = disabilities;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }

    public List<Diet> getDiets() {
        return diets;
    }

    public void setDiets(List<Diet> diets) {
        this.diets = diets;
    }

    public String getPatientHistory() {
        return patientHistory;
    }

    public void setPatientHistory(String patientHistory) {
        this.patientHistory = patientHistory;
    }

    public String getFollowupDoctorId() {
        return followupDoctorId;
    }

    public void setFollowupDoctorId(String followupDoctorId) {
        this.followupDoctorId = followupDoctorId;
    }
}

