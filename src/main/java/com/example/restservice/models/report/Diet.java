package com.example.restservice.models.report;

class Diet {
    private String dietName;
    private String description;

    public Diet(String dietName, String description) {
        this.dietName = dietName;
        this.description = description;
    }

    public Diet() {

    }

    public String getDietName() {
        return dietName;
    }

    public void setDietName(String dietName) {
        this.dietName = dietName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Diet{" +
                "dietname='" + dietName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}