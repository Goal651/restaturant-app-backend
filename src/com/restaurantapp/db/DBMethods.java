package com.restaurantapp.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBMethods {
    private DBConfig db = new DBConfig();
    final Connection connection = db.getConnection();

    // Method to insert a customer into the database
    public boolean addCustomer(String customerId, String name, int age, String address, String phoneNumber) {
        String sql = "INSERT INTO customers (customer_id, name, age, address, phone_number) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, customerId);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, age);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, phoneNumber);

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted.");
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error inserting customer: " + e.getMessage());
            return false;
        }
    }

    // Method to retrieve a customer by ID
    public void getCustomerById(String customerId) {
        String sql = "SELECT * FROM customers WHERE customer_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, customerId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Customer Details:");
                System.out.println("ID: " + resultSet.getString("customer_id"));
                System.out.println("Name: " + resultSet.getString("name"));
                System.out.println("Age: " + resultSet.getInt("age"));
                System.out.println("Address: " + resultSet.getString("address"));
                System.out.println("Phone: " + resultSet.getString("phone_number"));
            } else {
                System.out.println("No customer found with ID: " + customerId);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving customer: " + e.getMessage());
        }
    }

    // Method to retrieve all customers
    public void getAllCustomers() {
        String sql = "SELECT * FROM customers";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("All Customers:");
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getString("customer_id"));
                System.out.println("Name: " + resultSet.getString("name"));
                System.out.println("Age: " + resultSet.getInt("age"));
                System.out.println("Address: " + resultSet.getString("address"));
                System.out.println("Phone: " + resultSet.getString("phone_number"));
                System.out.println("---------");
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving customers: " + e.getMessage());
        }
    }
}
