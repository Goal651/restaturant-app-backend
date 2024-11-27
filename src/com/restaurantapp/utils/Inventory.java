package com.restaurantapp.utils;

import com.restaurantapp.model.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {
    private HashMap<String, Integer> stock; // Key: ingredient, Value: quantity

    public Inventory() {
        stock = new HashMap<>();
    }

    // Method to update stock of ingredients
    public void updateStock(String ingredient, int quantity) {
        stock.put(ingredient, stock.getOrDefault(ingredient, 0) + quantity);
        System.out.println("Updated stock for " + ingredient + ": " + stock.get(ingredient));
    }

    // Method to check stock of ingredients
    public void checkStockLevel(String ingredient) {
        System.out.println(ingredient + " stock level: " + stock.getOrDefault(ingredient, 0));
    }

    // Method to check stock for a MenuItem based on its ingredients
    public void checkMenuItemStock(MenuItem item) {
        System.out.println("Checking stock for " + item.toString());
        for (String ingredient : item.getIngredients()) {
            checkStockLevel(ingredient);
        }
    }

    // Inner class Menu to handle menu items
    public static class Menu {
        private ArrayList<MenuItem> menuItems;

        public Menu() {
            menuItems = new ArrayList<>();
        }

        public void addItem(MenuItem item) {
            menuItems.add(item);
            System.out.println(item + " added to menu.");
        }

        public void removeItem(MenuItem item) {
            menuItems.remove(item);
            System.out.println(item + " removed from menu.");
        }

        public void getMenu() {
            System.out.println("Restaurant Menu:");
            for (MenuItem item : menuItems) {
                item.getDetails();
            }
        }

        // Method to get a specific item by name
        public MenuItem getMenuItemByName(String itemName) {
            for (MenuItem item : menuItems) {
                if (item.toString().contains(itemName)) {
                    return item;
                }
            }
            return null; // If not found
        }
    }
}
