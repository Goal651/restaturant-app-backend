package com.restaurantapp.db;

import java.sql.Connection;
import java.sql.Statement;

public class DBInitializer {
    private Connection connection;

    public void initializeTables(Connection connection) {
        this.connection = connection;
        createCustomersTable();
        createStaffTable();
        createMenuTable();
    }

    private void createCustomersTable() {
        String createTableSQL = """
                    CREATE TABLE IF NOT EXISTS customers (
                        customer_id SERIAL PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        age INT NOT NULL,
                        address VARCHAR(255),
                        phone_number VARCHAR(15)
                    );
                """;

        executeSQL(createTableSQL, "customers");
    }

    private void createStaffTable() {
        String createTableSQL = """
                    CREATE TABLE IF NOT EXISTS staff (
                        staff_id VARCHAR(50) PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        age INT NOT NULL,
                        address VARCHAR(255),
                        role VARCHAR(50),
                        salary DOUBLE PRECISION
                    );
                """;

        executeSQL(createTableSQL, "staff");
    }

    private void createMenuTable() {
        String createTableSQL = """
                    CREATE TABLE IF NOT EXISTS menu (
                        item_id SERIAL PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        price DOUBLE PRECISION NOT NULL,
                        category VARCHAR(50),
                        description TEXT,
                        ingredients TEXT
                    );
                """;

        executeSQL(createTableSQL, "menu");
    }

    private void executeSQL(String sql, String tableName) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("Table '" + tableName + "' checked/created successfully.");
        } catch (Exception e) {
            System.err.println("Error creating/checking table '" + tableName + "': " + e.getMessage());
        }
    }
}
