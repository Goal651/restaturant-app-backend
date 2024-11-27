package com.restaurantapp.DAOS;

import com.restaurantapp.model.Staff;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffDAO {
    private final Connection connection;

    public StaffDAO(Connection connection) {
        this.connection = connection;
    }

    public void addStaff(Staff staff) {
        String query = "INSERT INTO staff (id, name, age, address, role, salary) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, staff.getEmployeeId());
            stmt.setString(2, staff.getName());
            stmt.setInt(3, staff.getAge());
            stmt.setString(4, staff.getAddress());
            stmt.setString(5, staff.getRole());
            stmt.setDouble(6, staff.getSalary());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding staff: " + e.getMessage());
        }
    }

    public List<Staff> getAllStaff() {
        List<Staff> staffList = new ArrayList<>();
        String query = "SELECT * FROM staff";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Staff staff = new Staff(
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("address"),
                        rs.getString("id"),
                        rs.getString("role"),
                        rs.getDouble("salary")
                );
                staffList.add(staff);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving staff: " + e.getMessage());
        }
        return staffList;
    }
}
