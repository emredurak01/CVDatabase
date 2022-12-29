package com.example.cvdatabase.Helpers;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {

    private Connection connection = null;
    File file = new File(Config.DB_FILE_NAME);
    boolean firstRun = !file.exists();

    public Connection connectDB() {

        try {

            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(Config.DB_URL);

            if (firstRun) {
                Statement stat = connection.createStatement();
                stat.executeUpdate("CREATE TABLE Person\n" +
                        "(\n" +
                        "    id        integer\n" +
                        "        constraint Person_pk\n" +
                        "            primary key autoincrement,\n" +
                        "    name      text,\n" +
                        "    surname   text,\n" +
                        "    birthdate text,\n" +
                        "    email     text,\n" +
                        "    phone     text,\n" +
                        "    about_me  text\n" +
                        ", interests text, skills text)");

                stat.executeUpdate("CREATE TABLE \"Education\"\n" +
                        "(\n" +
                        "    id          INTEGER\n" +
                        "        constraint Education_pk\n" +
                        "            primary key autoincrement,\n" +
                        "    person_id   INTEGER\n" +
                        "        constraint education_fk\n" +
                        "            references Person\n" +
                        "            on update cascade on delete cascade,\n" +
                        "    school_name TEXT,\n" +
                        "    degree      TEXT,\n" +
                        "    study_field TEXT,\n" +
                        "    start_date  TEXT,\n" +
                        "    end_date    TEXT\n" +
                        ")");
                stat.executeUpdate("CREATE TABLE Experience\n" +
                        "(\n" +
                        "    id              integer\n" +
                        "        constraint Experience_pk\n" +
                        "            primary key autoincrement,\n" +
                        "    person_id       integer\n" +
                        "        constraint experience_fk\n" +
                        "            references Person\n" +
                        "            on update cascade on delete cascade,\n" +
                        "    title           text,\n" +
                        "    employment_type text,\n" +
                        "    company_name    text,\n" +
                        "    location        text,\n" +
                        "    location_type   text,\n" +
                        "    industry        text,\n" +
                        "    description     text,\n" +
                        "    start_date      text,\n" +
                        "    end_date        text\n" +
                        ")");
                stat.executeUpdate("CREATE TABLE Publication\n" +
                        "(\n" +
                        "    id               integer\n" +
                        "        constraint Publication_pk\n" +
                        "            primary key autoincrement,\n" +
                        "    person_id        integer\n" +
                        "        constraint publication_fk\n" +
                        "            references Person\n" +
                        "            on update cascade on delete cascade,\n" +
                        "    title            text,\n" +
                        "    publisher        text,\n" +
                        "    publication_date text\n" +
                        ")");
            }

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;

    }

    public static Connection getInstance() {

        DatabaseConnector dbConnector = new DatabaseConnector();
        return dbConnector.connectDB();

    }

}

