package com.example.cvdatabase.Model;

public class Tag {

    private int id;
    private String name;

    public Tag(String name) {
        this.name = name;
    }

    public Tag() {
    }

    // Getters & Setters
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
}
