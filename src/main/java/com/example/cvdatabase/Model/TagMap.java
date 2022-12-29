package com.example.cvdatabase.Model;

public class TagMap {

    private int id;
    private int personID;
    private int tagID;

    public TagMap(int personID, int tagID) {
        this.personID = personID;
        this.tagID = tagID;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonID() {
        return personID;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public int getTagID() {
        return tagID;
    }

    public void setTagID(int tagID) {
        this.tagID = tagID;
    }
}
