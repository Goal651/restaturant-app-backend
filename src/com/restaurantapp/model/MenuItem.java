package com.restaurantapp.model;

import java.util.ArrayList;
import com.restaurantapp.DAOS.MenuItemDAO;

public class MenuItem {
    private String itemName;
    private double price;
    private String category;
    private String description;
    private ArrayList<String> ingredients; // Added ingredients list

    public MenuItem(String itemName, double price, String category, String description, ArrayList<String> ingredients) {
        this.itemName = itemName;
        this.price = price;
        this.category = category;
        this.description = description;
        this.ingredients = ingredients;
    }

    public void getDetails() {
        System.out.println("Item: " + itemName + ", Price: " + price + ", Category: " + category);
        System.out.println("Description: " + description);
        System.out.println("Ingredients: " + String.join(", ", ingredients));
    }

    public void updatePrice(double newPrice) {
        this.price = newPrice;
    }

    public double getPrice() {
        return this.price;
    }

    public String getCategory(){
        return this.category;
    }

    public String getDescription() {
        return this.description;
    }

    public String getItemName() {
        return this.itemName;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    @Override
    public String toString() {
        return itemName + " (" + category + "): " + description + " - $" + price;
    }
}
