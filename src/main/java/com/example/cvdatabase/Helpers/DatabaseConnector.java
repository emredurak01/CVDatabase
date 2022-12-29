package com.example.cvdatabase.Helpers;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private Connection connection = null;

    public Connection connectDB(){

        try {

            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(Config.DB_URL);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        return connection;

    }

    public static Connection getInstance(){

        DatabaseConnector dbConnector = new DatabaseConnector();
        return dbConnector.connectDB();

    }

}

