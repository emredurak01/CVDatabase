package com.example.cvdatabase.Model;

public class Experience {

    private int id;
    private int personID;
    private String title;
    private String employmentType; //full-time, part-time, freelance, seasonal etc.
    private String companyName;
    private String location;
    private String locationType; // remote, on-site, hybrid
    private String industry;
    private String description;
    private String startDate;
    private String endDate;

    public Experience(int personID, String title, String employmentType, String companyName, String location, String locationType, String industry, String description, String startDate, String endDate) {
        this.personID = personID;
        this.title = title;
        this.employmentType = employmentType;
        this.companyName = companyName;
        this.location = location;
        this.locationType = locationType;
        this.industry = industry;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}

