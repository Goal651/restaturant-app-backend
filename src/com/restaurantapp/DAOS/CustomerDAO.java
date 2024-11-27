package com.restaurantapp.DAOS;

import com.restaurantapp.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    private final Connection connection;

    public CustomerDAO(Connection connection) {
        this.connection = connection;
    }

    // Add a customer to the database
    public void addCustomer(Customer customer) {
        String query = "INSERT INTO customers (id, name, age, address, phone) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, customer.getCustomerId());
            stmt.setString(2, customer.getName());
            stmt.setInt(3, customer.getAge());
            stmt.setString(4, customer.getAddress());
            stmt.setString(5, customer.getPhoneNumber());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding customer: " + e.getMessage());
        }
    }

    // Retrieve all customers from the database
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customers";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("address"),
                        rs.getString("id"),
                        rs.getString("phone")
                );
                customers.add(customer);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving customers: " + e.getMessage());
        }
        return customers;
    }

    // Retrieve a customer by their ID
    public Customer getCustomerById(String customerId) {
        String query = "SELECT * FROM customers WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, customerId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Customer(
                            rs.getString("name"),
                            rs.getInt("age"),
                            rs.getString("address"),
                            rs.getString("id"),
                            rs.getString("phone")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving customer: " + e.getMessage());
        }
        return null;
    }

    // Update an existing customer's details
    public boolean updateCustomer(Customer customer) {
        String query = "UPDATE customers SET name = ?, age = ?, address = ?, phone = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, customer.getName());
            stmt.setInt(2, customer.getAge());
            stmt.setString(3, customer.getAddress());
            stmt.setString(4, customer.getPhoneNumber());
            stmt.setString(5, customer.getCustomerId());
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.err.println("Error updating customer: " + e.getMessage());
        }
        return false;
    }

    // Delete a customer from the database
    public boolean deleteCustomer(String customerId) {
        String query = "DELETE FROM customers WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, customerId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting customer: " + e.getMessage());
        }
        return false;
    }

    // Search for customers by name
    public List<Customer> searchCustomersByName(String name) {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customers WHERE name LIKE ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, "%" + name + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Customer customer = new Customer(
                            rs.getString("name"),
                            rs.getInt("age"),
                            rs.getString("address"),
                            rs.getString("id"),
                            rs.getString("phone")
                    );
                    customers.add(customer);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error searching customers: " + e.getMessage());
        }
        return customers;
    }
}
