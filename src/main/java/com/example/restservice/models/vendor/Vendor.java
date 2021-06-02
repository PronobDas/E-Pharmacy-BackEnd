package com.example.restservice.models.vendor;

import org.springframework.data.annotation.Id;

public class Vendor {
    @Id
    private String id;

    private String name;
    private String locationId;
    private String contactNo;
    private String status;

    public Vendor(String name, String locationId, String contactNo, String status) {
        this.name = name;
        this.locationId = locationId;
        this.contactNo = contactNo;
        this.status = status;
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

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String locationId) {
        this.contactNo = contactNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Vendor{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", locationId='" + locationId + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
