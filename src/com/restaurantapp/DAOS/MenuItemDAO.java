package com.restaurantapp.DAOS;

import com.restaurantapp.model.MenuItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuItemDAO {
    private final Connection connection;

    public MenuItemDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean addMenuItem(String name, double price, String category, String description, ArrayList<String> ingredients) {
        String query = "INSERT INTO menu (name, price, category, description, ingredients) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setDouble(2, price);
            stmt.setString(3, category);
            stmt.setString(4, description);
            stmt.setString(5, String.join(",", ingredients));
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error adding menu item: " + e.getMessage());
            return false;
        }
    }

    public List<MenuItem> getAllMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();
        String query = "SELECT * FROM menu";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                MenuItem menuItem = new MenuItem(
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("category"),
                        rs.getString("description"),
                        new ArrayList<>(List.of(rs.getString("ingredients").split(","))));
                menuItems.add(menuItem);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving menu items: " + e.getMessage());
        }
        return menuItems;
    }

    public MenuItem getMenuItemById(int itemId) {
        String query = "SELECT * FROM menu WHERE item_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, itemId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new MenuItem(
                            rs.getString("name"),
                            rs.getDouble("price"),
                            rs.getString("category"),
                            rs.getString("description"),
                            new ArrayList<>(List.of(rs.getString("ingredients").split(","))));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving menu item by ID: " + e.getMessage());
        }
        return null;
    }

    public boolean updateMenuItem(int itemId, String name, double price, String category, String description, ArrayList<String> ingredients) {
        String query = "UPDATE menu SET name = ?, price = ?, category = ?, description = ?, ingredients = ? WHERE item_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setDouble(2, price);
            stmt.setString(3, category);
            stmt.setString(4, description);
            stmt.setString(5, String.join(",", ingredients));
            stmt.setInt(6, itemId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error updating menu item: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteMenuItem(int itemId) {
        String query = "DELETE FROM menu WHERE item_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, itemId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting menu item: " + e.getMessage());
            return false;
        }
    }
}
