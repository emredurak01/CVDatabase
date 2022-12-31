package com.example.cvdatabase.Model;

public class Publication {

    private int id;
    private int personID;
    private String title;
    private String publisher;
    private String publicationDate;

    public Publication(String title, String publisher, String publicationDate) {
        this.title = title;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
    }

    public Publication() {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }
}

