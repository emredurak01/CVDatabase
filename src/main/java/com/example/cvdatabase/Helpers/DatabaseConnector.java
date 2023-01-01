package com.example.cvdatabase.Helpers;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {

    private static Connection connection = null;
    File file = new File(Config.DB_FILE_NAME);
    boolean firstRun = !file.exists();

    public Connection connectDB() {

        try {

            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(Config.DB_URL);

            if (firstRun) {
                Statement stat = connection.createStatement();
                stat.executeUpdate("""
                        CREATE TABLE Person
                        (
                            id        integer
                                constraint Person_pk
                                    primary key autoincrement,
                            name      text,
                            surname   text,
                            birthdate text,
                            email     text,
                            phone     text,
                            about_me  text,
                            interests text,
                            skills text)""");

                stat.executeUpdate("""
                        CREATE TABLE "Education"
                        (
                            id          INTEGER
                                constraint Education_pk
                                    primary key autoincrement,
                            person_id   INTEGER
                                constraint education_fk
                                    references Person
                                    on update cascade on delete cascade,
                            school_name TEXT,
                            degree      TEXT,
                            study_field TEXT,
                            start_date  TEXT,
                            end_date    TEXT
                        )""");
                stat.executeUpdate("""
                        CREATE TABLE Experience
                        (
                            id              integer
                                constraint Experience_pk
                                    primary key autoincrement,
                            person_id       integer
                                constraint experience_fk
                                    references Person
                                    on update cascade on delete cascade,
                            title           text,
                            employment_type text,
                            company_name    text,
                            location        text,
                            location_type   text,
                            industry        text,
                            description     text,
                            start_date      text,
                            end_date        text
                        )""");
                stat.executeUpdate("""
                        CREATE TABLE Publication
                        (
                            id               integer
                                constraint Publication_pk
                                    primary key autoincrement,
                            person_id        integer
                                constraint publication_fk
                                    references Person
                                    on update cascade on delete cascade,
                            title            text,
                            publisher        text,
                            publication_date text
                        )""");

                stat.executeUpdate("""
                        create table Tag
                        (
                            id   INTEGER
                                constraint Tag_pk
                                    primary key autoincrement,
                            name TEXT
                        )""");

                stat.executeUpdate("""
                        create table TagMap
                        (
                            id        INTEGER
                                constraint TagMap_pk
                                    primary key autoincrement,
                            person_id INTEGER
                                references Person
                                    on update cascade on delete cascade,
                            tag_id    INTEGER
                                references Tag
                                    on update cascade on delete cascade
                        )""");

            }

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;

    }

    public static Connection getInstance() {

        if(connection == null){
            DatabaseConnector dbConnector = new DatabaseConnector();
            connection = dbConnector.connectDB();
        }

        return connection;

    }

}

