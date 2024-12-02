package com.restaurantapp.utils;

import com.restaurantapp.DAOS.MenuItemDAO;
import com.restaurantapp.db.DBConfig;
import com.restaurantapp.model.MenuItem;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    private ArrayList<MenuItem> menuItems;
    private final Connection connection = new DBConfig().getConnection();

    public Menu() {
        menuItems = new ArrayList<>();
    }

    // Add a MenuItem to the menu
    public void addItem(MenuItem item) {
        menuItems.add(item);
        System.out.println(item + " added to menu.");
    }

    // Remove a MenuItem from the menu
    public void removeItem(MenuItem item) {
        menuItems.remove(item);
        System.out.println(item + " removed from menu.");
    }

    // Print out the entire menu
    public void getMenu() {
        List<MenuItem> menuItem=new MenuItemDAO(connection).getAllMenuItems();
        System.out.println("Restaurant Menu:");
        for (MenuItem item : menuItem) {
            item.getDetails();
        }
    }
}
