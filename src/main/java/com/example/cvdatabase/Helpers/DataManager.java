package com.example.cvdatabase.Helpers;

import com.example.cvdatabase.Controller.Controller;
import com.example.cvdatabase.Model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class DataManager {

    private final static DataManager INSTANCE = new DataManager();

    private DataManager() {
    }

    public static DataManager getInstance() {
        return INSTANCE;
    }

    public Tag PullTagByName(String name) {

        String q = "select * from Tag where name = ?";
        Tag tag = null;

        try {
            PreparedStatement ps = DatabaseConnector.getInstance().prepareStatement(q);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                tag = new Tag(rs.getString("name"));
                tag.setId(rs.getInt("id"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tag;

    }

    public void UpdateTag(String name, int tagID) {

        String q = "update Tag set name = ? where id = ?";

        try {
            PreparedStatement ps = DatabaseConnector.getInstance().prepareStatement(q);
            ps.setString(1, name);
            ps.setInt(2, tagID);

            if (ps.executeUpdate() > 0) {

                Controller.createAlert("Tag is updated", "Confirmation");


            } else {

                Controller.createAlert("An error occurred.", "Error");


            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void AddTag(int personID, Tag tag) {

        String q1 = "insert into Tag(name) values(?)";
        String q2 = "insert into TagMap(person_id,tag_id) values(?,?)";
        PreparedStatement ps1;
        PreparedStatement ps2;

        try {
            ps1 = DatabaseConnector.getInstance().prepareStatement(q1);
            ps2 = DatabaseConnector.getInstance().prepareStatement(q2);
            ps1.setString(1, tag.getName());

            Tag pulledTag = PullTagByName(tag.getName());

            if (pulledTag == null) {

                if (ps1.executeUpdate() > 0) {

                    ps2.setInt(1, personID);
                    ps2.setInt(2, PullTagByName(tag.getName()).getId());


                    if (ps2.executeUpdate() > 0) {

                        Controller.createAlert("Tag is created for the selected CV", "Confirmation");

                    } else {

                        Controller.createAlert("Tag is not created.An error occurred.", "Error");

                    }

                }

            } else {

                ps2.setInt(1, personID);
                ps2.setInt(2, PullTagByName(tag.getName()).getId());

                if (ps2.executeUpdate() > 0) {

                    Controller.createAlert("Tag is created for the selected CV", "Confirmation");

                } else {

                    Controller.createAlert("Tag is not created.An error occurred.", "Error");

                }

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Tag> PullTags(int personID) {

        ArrayList<Tag> tags = new ArrayList<>();

        String q1 = "select * from TagMap where person_id = ?";
        String q2 = "select * from Tag where id = ?";

        PreparedStatement ps1;
        PreparedStatement ps2;
        try {
            ps1 = DatabaseConnector.getInstance().prepareStatement(q1);
            ps2 = DatabaseConnector.getInstance().prepareStatement(q2);
            ps1.setInt(1, personID);
            ResultSet rs = ps1.executeQuery();

            while (rs.next()) {

                ps2.setInt(1, rs.getInt("tag_id"));
                ResultSet rs2 = ps2.executeQuery();

                while (rs2.next()) {

                    Tag tag = new Tag(rs2.getString("name"));
                    tag.setId(rs2.getInt("id"));
                    tags.add(tag);

                }

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tags;
    }

    public ArrayList<Person> PullPersons() {

        String q = "select * from Person";
        ArrayList<Person> persons = new ArrayList<>();

        try {
            ResultSet rs = DatabaseConnector.getInstance().createStatement().executeQuery(q);

            Person d;
            while (rs.next()) {

                d = new Person();
                d.setId(rs.getInt("id"));
                d.setName(rs.getString("name"));
                d.setSurname(rs.getString("surname"));
                d.setBirthdate(rs.getString("birthdate"));
                d.setEmail(rs.getString("email"));
                d.setPhone(rs.getString("phone"));

                ArrayList<String> interestsList;
                interestsList = new ArrayList<String>(Arrays.asList(rs.getString("interests").split(",")));
                d.setInterests(interestsList);

                ArrayList<String> skillsList;
                skillsList = new ArrayList<String>(Arrays.asList(rs.getString("skills").split(",")));

                d.setSkills(skillsList);

                d.setEducation(PullEducations(d.getId()));
                d.setExperiences(PullExperiences(d.getId()));
                d.setPublications(PullPublications(d.getId()));
                d.setTags(PullTags(d.getId()));

                persons.add(d);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return persons;

    }

    public ArrayList<Education> PullEducations(int personID) {

        ArrayList<Education> educations = new ArrayList<>();

        String q = "select * from Education where person_id = ?";
        try {
            PreparedStatement ps = DatabaseConnector.getInstance().prepareStatement(q);
            ps.setInt(1, personID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Education education = new Education();
                education.setId(rs.getInt("id"));
                education.setPersonID(rs.getInt("person_id"));
                education.setName(rs.getString("school_name"));
                education.setStartDate(rs.getString("start_date"));
                education.setEndDate(rs.getString("end_date"));
                educations.add(education);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return educations;
    }

    public ArrayList<Experience> PullExperiences(int personID) {

        ArrayList<Experience> experiences = new ArrayList<>();

        String q = "select * from Experience where person_id = ?";
        try {
            PreparedStatement ps = DatabaseConnector.getInstance().prepareStatement(q);
            ps.setInt(1, personID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Experience experience = new Experience();
                experience.setId(rs.getInt("id"));
                experience.setPersonID(rs.getInt("person_id"));
                experience.setTitle(rs.getString("title"));
                experience.setStartDate(rs.getString("start_date"));
                experience.setEndDate(rs.getString("end_date"));
                experiences.add(experience);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return experiences;

    }

    public ArrayList<Publication> PullPublications(int personID) {

        ArrayList<Publication> publications = new ArrayList<>();

        String q = "select * from Publication where person_id = ?";
        try {
            PreparedStatement ps = DatabaseConnector.getInstance().prepareStatement(q);
            ps.setInt(1, personID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Publication publication = new Publication();
                publication.setId(rs.getInt("id"));
                publication.setPersonID(rs.getInt("person_id"));
                publication.setTitle(rs.getString("title"));
                publication.setPublisher(rs.getString("publisher"));
                publication.setPublicationDate(rs.getString("publication_date"));
                publications.add(publication);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return publications;

    }

    public String PullSkills(int personID) {

        String skills = "";

        String q = "select * from Person where id = ?";
        try {
            PreparedStatement ps = DatabaseConnector.getInstance().prepareStatement(q);
            ps.setInt(1, personID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                skills = rs.getString("skills");

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return skills;


    }

    public String PullInterests(int personID) {

        String interests = "";

        String q = "select * from Person where id = ?";
        try {
            PreparedStatement ps = DatabaseConnector.getInstance().prepareStatement(q);
            ps.setInt(1, personID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                interests = rs.getString("interests");

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        return interests;

    }


    public void DeletePublication(int personID, String title) {

        String q = "delete from Publication where person_id = ? and title = ?";
        try {
            PreparedStatement ps = DatabaseConnector.getInstance().prepareStatement(q);
            ps.setInt(1, personID);
            ps.setString(2, title);
            if (ps.executeUpdate() > 0) {

                Controller.createAlert("Deleted successfully.", "Remove Publication");

            } else {

                Controller.createAlert("An error occurred.", "Error");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void DeleteTag(int personID, int tagID) {

        String q = "delete from TagMap where person_id = ? and tag_id = ?";
        try {
            PreparedStatement ps = DatabaseConnector.getInstance().prepareStatement(q);
            ps.setInt(1, personID);
            ps.setInt(2, tagID);
            if (ps.executeUpdate() > 0) {

                Controller.createAlert("Deleted successfully.", "Remove Tag");

            } else {

                Controller.createAlert("An error occurred.", "Error");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void DeleteExperience(int personID, String title) {

        String q = "delete from Experience where person_id = ? and title = ?";
        try {
            PreparedStatement ps = DatabaseConnector.getInstance().prepareStatement(q);
            ps.setInt(1, personID);
            ps.setString(2, title);
            if (ps.executeUpdate() > 0) {

                Controller.createAlert("Deleted successfully.", "Remove Experience");

            } else {

                Controller.createAlert("An error occurred.", "Error");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void DeleteEducation(int personID, String name) {

        String q = "delete from Education where person_id = ? and school_name = ?";
        try {
            PreparedStatement ps = DatabaseConnector.getInstance().prepareStatement(q);
            ps.setInt(1, personID);
            ps.setString(2, name);
            if (ps.executeUpdate() > 0) {

                Controller.createAlert("Deleted successfully.", "Remove Education");

            } else {

                Controller.createAlert("An error occurred.", "Error");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void UpdateInterest(int personID, String interests) {

        String q = "update Person set interests = ? where id = ?";

        //interests = interests.replace(',',' ');
        //interests = interests.trim();

        try {
            PreparedStatement ps = DatabaseConnector.getInstance().prepareStatement(q);
            ps.setString(1, interests);
            ps.setInt(2, personID);

            if (ps.executeUpdate() > 0) {

                Controller.createAlert("Deleted successfully.", "Remove Interests");

            } else {

                Controller.createAlert("An error occurred.", "Error");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void UpdateSkills(int personID, String skills) {

        String q = "update Person set skills = ? where id = ?";

        //interests = interests.replace(',',' ');
        //interests = interests.trim();

        try {
            PreparedStatement ps = DatabaseConnector.getInstance().prepareStatement(q);
            ps.setString(1, skills);
            ps.setInt(2, personID);

            if (ps.executeUpdate() > 0) {

                Controller.createAlert("Deleted successfully.", "Remove Skill");

            } else {

                Controller.createAlert("An error occurred.", "Error");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
