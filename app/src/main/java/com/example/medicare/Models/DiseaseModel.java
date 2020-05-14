package com.example.medicare.Models;

public class DiseaseModel {
    private String Title;
    private String Description;

    public DiseaseModel(String title, String description) {
        Title = title;
        Description = description;
    }

    public DiseaseModel() {
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
