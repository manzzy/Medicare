package com.example.medicare.Models;

public class Disease {
    int id;
    String name;
    String shortName;
    String shortDescription;
    String description;

    public Disease(int id, String name, String shortDescription) {
        this.id = id;
        this.name = name;
        this.shortDescription = shortDescription;
    }

    public Disease(int id, String name, String shortName, String shortDescription, String description) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.shortDescription = shortDescription;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
