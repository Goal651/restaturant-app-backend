package com.restaurantapp.utils;

import com.restaurantapp.model.MenuItem;

import java.util.ArrayList;

public class Menu {
    private ArrayList<MenuItem> menuItems;

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
        System.out.println("Restaurant Menu:");
        for (MenuItem item : menuItems) {
            item.getDetails();
        }
    }
}
