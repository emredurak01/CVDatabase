package com.example.cvdatabase.Helpers;

import com.example.cvdatabase.Model.Person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataManager {

    private final static DataManager INSTANCE = new DataManager();

    private DataManager() {}

    public static DataManager getInstance() {
        return INSTANCE;
    }


    public static ArrayList<Person> PullPersons(){

        String q = "select * from Person";
        ArrayList<Person> persons = new ArrayList<>();

        try {
            ResultSet rs = DatabaseConnector.getInstance().createStatement().executeQuery(q);

            Person d;
            while (rs.next()){

                d = new Person();
                d.setId(rs.getInt("id"));
                d.setName(rs.getString("name"));
                d.setSurname(rs.getString("surname"));
                d.setBirthdate(rs.getString("birthdate"));
                d.setEmail(rs.getString("email"));
                d.setPhone(rs.getString("phone"));
                ArrayList<String> list = new ArrayList<>();
                list.add(rs.getString("interests"));
                d.setInterests(list);
                list.clear();
                list.add(rs.getString("skills"));
                d.setSkills(list);
                list.clear();

                persons.add(d);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return persons;

    }

}
