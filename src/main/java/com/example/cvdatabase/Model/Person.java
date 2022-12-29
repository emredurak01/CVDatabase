package com.example.cvdatabase.Model;

import java.util.ArrayList;

public class Person {
    private int id;
    private String name;
    private String surname;
    private String birthdate;
    private String email;
    private String phone;
    private ArrayList<Education> education;
    private ArrayList<Experience> experiences;
    private ArrayList<Publication> publications;
    private ArrayList<String> interests;
    private ArrayList<String> skills;
    private ArrayList<Tag> tags;

    public Person(){

    }

    public Person(String name, String surname, String birthdate, String email, String phone, ArrayList<Education> education) {
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.email = email;
        this.phone = phone;
        this.education = education;
    }

    public Person(String name, String surname, String birthdate, String email, String phone) {
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.email = email;
        this.phone = phone;
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

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<Education> getEducation() {
        return education;
    }

    public void setEducation(ArrayList<Education> education) {
        this.education = education;
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


    public ArrayList<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(ArrayList<Experience> experiences) {
        this.experiences = experiences;
    }

    public ArrayList<Publication> getPublications() {
        return publications;
    }

    public void setPublications(ArrayList<Publication> publications) {
        this.publications = publications;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
