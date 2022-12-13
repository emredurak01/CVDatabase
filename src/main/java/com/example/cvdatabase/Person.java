package com.example.cvdatabase;

import java.util.ArrayList;

public class Person {
    int id;
    String name;
    String surname;
    String dateOfBirth;
    String email;
    int phone;
    ArrayList<String> education;
    ArrayList<String> experiences;
    ArrayList<String> publications;
    ArrayList<String> interests;
    ArrayList<String> skills;
    ArrayList<String> tags;

    public Person(int id, String name, String surname, String dateOfBirth, String email, int phone, ArrayList<String> education) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phone = phone;
        this.education = education;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public ArrayList<String> getEducation() {
        return education;
    }

    public void setEducation(ArrayList<String> education) {
        this.education = education;
    }

    public ArrayList<String> getExperiences() {
        return experiences;
    }

    public void setExperiences(ArrayList<String> experiences) {
        this.experiences = experiences;
    }

    public ArrayList<String> getPublications() {
        return publications;
    }

    public void setPublications(ArrayList<String> publications) {
        this.publications = publications;
    }

    public ArrayList<String> getInterests() {
        return interests;
    }

    public void setInterests(ArrayList<String> interests) {
        this.interests = interests;
    }

    public ArrayList<String> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<String> skills) {
        this.skills = skills;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }
}
