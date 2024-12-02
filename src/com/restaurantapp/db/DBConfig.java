package com.restaurantapp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBConfig {
    private final String jdbcUrl = "jdbc:postgresql://localhost:5432/restaurantdb";
    private final String username = "postgres";
    private final String password = "postgres";
    private final DBInitializer initializeDb=new DBInitializer();
    private Connection connection = null;

    // Method to connect to the database
    public Connection connectToDb() {
        try {
            Class.forName("org.postgresql.Driver");
            if (connection == null || connection.isClosed()) {
                this.connection = DriverManager.getConnection(jdbcUrl, username, password);
                System.out.println("Connected to the database successfully.");
                initializeDb.initializeTables(connection);
                return this.connection;
            }
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL JDBC Driver not found.");
        } catch (SQLException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
        }
        return this.connection;
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
