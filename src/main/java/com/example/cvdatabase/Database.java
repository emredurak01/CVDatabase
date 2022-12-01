package com.example.cvdatabase;

import java.io.File;
import java.sql.*;

public class Database {

    private final String dbfile;
    private Connection conn;
    private PreparedStatement insertSQL;
    private PreparedStatement selectSQL;

    public Database() {
        dbfile = "data.db";
        conn = null;
        File file = new File(dbfile);
        boolean firstRun = !file.exists();

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + dbfile);

            if (firstRun) {
                Statement stat = conn.createStatement();
                stat.executeUpdate("CREATE TABLE Person(" + "id INTEGER PRIMARY KEY," + "name TEXT);");
            }
            insertSQL = conn.prepareStatement("INSERT INTO Person(name) values(?)");
            selectSQL = conn.prepareStatement("SELECT * FROM Person");


        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }

    }

    public void addPerson(String name) {
        try {
            insertSQL.setString(1, name);
            insertSQL.execute();
        } catch (SQLException e) {
            System.err.print(e);
        }
    }

    public void listPersons() {
        try {
            ResultSet rs = selectSQL.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
    }


}