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

    public void addMenuItem(MenuItem menuItem) {
        String query = "INSERT INTO menu_items (name, price, category, description, ingredients) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, menuItem.getName());
            stmt.setDouble(2, menuItem.getPrice());
            stmt.setString(3, menuItem.getCategory());
            stmt.setString(4, menuItem.getDescription());
            stmt.setString(5, String.join(",", menuItem.getIngredients()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding menu item: " + e.getMessage());
        }
    }

    public List<MenuItem> getAllMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();
        String query = "SELECT * FROM menu_items";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                MenuItem menuItem = new MenuItem(
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getString("category"),
                        rs.getString("description"),
                        new ArrayList<>(List.of(rs.getString("ingredients").split(",")))
                );
                menuItems.add(menuItem);
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving menu items: " + e.getMessage());
        }
        return menuItems;
    }
}
