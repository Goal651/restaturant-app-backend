package com.restaurantapp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {
    private final String jdbcUrl = System.getenv("DB_URL");
    private final String username = System.getenv("DB_USERNAME");
    private final String password = System.getenv("DB_PASSWORD");
    private Connection connection = null;

    // Method to connect to the database
    public Connection connectToDb() {
        try {
            Class.forName("org.postgresql.Driver");
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(jdbcUrl, username, password);
                System.out.println("Connected to the database successfully.");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL JDBC Driver not found.");
        } catch (SQLException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
        }
        return connection;
    }

    // Method to get the database connection
    public Connection getConnection() {
        if (connection == null) {
            return connectToDb();
        }
        return connection;
    }

    // Method to close the database connection
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing database connection: " + e.getMessage());
        }
    }
}
