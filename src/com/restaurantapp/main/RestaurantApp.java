package com.restaurantapp.main;

import com.restaurantapp.DAOS.CustomerDAO;
import com.restaurantapp.DAOS.MenuItemDAO;
import com.restaurantapp.db.DBConfig;
import com.restaurantapp.model.Customer;
import com.restaurantapp.model.MenuItem;
import com.restaurantapp.model.Staff;
import com.restaurantapp.utils.Menu;
import com.restaurantapp.validator.InputValidator;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RestaurantApp {
    private final Scanner scanner = new Scanner(System.in);
    private final Menu menu = new Menu();
    private final DBConfig dbConfig = new DBConfig();
    private final Connection connection;
    private final InputValidator inputValidator = new InputValidator();


    public RestaurantApp() {

        // Initialize the database connection
        connection = dbConfig.connectToDb();
        if (connection == null) {
            System.err.println("Database connection failed. Exiting...");
            System.exit(1);
        }

        while (true) {
            displayMainMenu();
            int choice = inputValidator.getValidatedInt();

            switch (choice) {
                case 1 -> addCustomer();
                case 2 -> addStaff();
                case 3 -> addMenuItem();
                case 4 -> placeOrder();
                case 5 -> viewMenu();
                case 6 -> viewCustomers();
                case 7 -> viewStaff();
                case 8 -> searchCustomerById();
                case 9 -> searchStaffByName();
                case 10 -> editMenuItem();
                case 11 -> deleteMenuItem();
                case 12 -> exitApp();
                default -> System.out.println("Invalid choice. Please try again.");
            }

        }
    }


    private void displayMainMenu() {
        System.out.println("\n=== Restaurant Management System ===");
        System.out.println("1. Add Customer");
        System.out.println("2. Add Staff");
        System.out.println("3. Add Menu Item");
        System.out.println("4. Place Order");
        System.out.println("5. View Menu");
        System.out.println("6. View Customers");
        System.out.println("7. View Staff");
        System.out.println("8. Search Customer by ID");
        System.out.println("9. Search Staff by Name");
        System.out.println("10. Edit Menu Item");
        System.out.println("11. Delete Menu Item");
        System.out.println("12. Exit");
        System.out.print("Enter your choice: ");
    }


    private void addCustomer() {
        CustomerDAO customerDAO = new CustomerDAO(connection);
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter customer age (must be a positive number): ");
        int age = inputValidator.getValidatedInt();
        System.out.print("Enter customer address: ");
        String address = scanner.nextLine();
        System.out.print("Enter customer phone number: ");
        String phone = scanner.nextLine();
        Customer customer = new Customer(name, age, address, phone);
        boolean result = customerDAO.addCustomer(customer);
        if (result) {
            System.out.println("Customer added successfully!");
        } else {
            System.out.println("An error occurred while adding customer");
        }

    }

    private void addStaff() {
        System.out.print("Enter staff name: ");
        String name = scanner.nextLine();
        System.out.print("Enter staff age (must be a positive number): ");
        int age = inputValidator.getValidatedInt();
        System.out.print("Enter staff address: ");
        String address = scanner.nextLine();
        System.out.print("Enter staff role: ");
        String role = scanner.nextLine();
        System.out.print("Enter staff salary (must be a positive number): ");
        double salary = inputValidator.getValidatedDouble();
        System.out.println("Enter employee id: ");
        String employeeID = scanner.nextLine();

        Staff staff = new Staff(name, age, address, employeeID, role, salary);
        // Staff DAO functionality would save the staff to the database here
        System.out.println("Staff added successfully!");
    }

    private void addMenuItem() {
        MenuItemDAO menuItemDAO = new MenuItemDAO(connection);
        System.out.println(connection);
        System.out.print("Enter menu item name: ");
        String name = scanner.nextLine();
        System.out.print("Enter menu item price (must be positive): ");
        double price = inputValidator.getValidatedDouble();
        System.out.print("Enter menu item category: ");
        String category = scanner.nextLine();
        System.out.print("Enter menu item description: ");
        String description = scanner.nextLine();
        System.out.print("Enter ingredients (comma-separated): ");
        String[] ingredientsArray = scanner.nextLine().split(",");
        ArrayList<String> ingredients = new ArrayList<>();
        for (String ingredient : ingredientsArray) {
            ingredients.add(ingredient.trim());
        }

        boolean result = menuItemDAO.addMenuItem(name, price, category, description, ingredients);
        if (result) {
            System.out.println("Menu item added successfully!");
        } else {
            System.out.println("An error occurred while adding menu item");
        }

    }

    private void placeOrder() {
        CustomerDAO customerDAO = new CustomerDAO(connection);
        System.out.print("Enter customer ID: ");
        String customerId = scanner.nextLine();
        List<Customer> customers = customerDAO.getAllCustomers();

        System.out.print("Enter the item to order: ");
        String orderItem = scanner.nextLine();
        System.out.println("Order placed successfully!");
    }

    private void viewMenu() {
        menu.getMenu();
    }

    private void viewCustomers() {
        CustomerDAO customerDAO = new CustomerDAO(connection);
        List<Customer> customers = customerDAO.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No customers available.");
        } else {
            customers.forEach(System.out::println);
        }
    }

    private void viewStaff() {
        // Staff DAO functionality would retrieve and display staff data
    }

    private void searchCustomerById() {
        CustomerDAO customerDAO = new CustomerDAO(connection);
        System.out.print("Enter Customer ID: ");
        int id = scanner.nextInt();
        Customer customer = customerDAO.getCustomerById(id);
        if (customer == null) {
            System.out.println("No customer found with ID: " + id);
        } else {
            System.out.println(customer);
        }
    }

    private void searchStaffByName() {
        System.out.print("Enter Staff Name: ");
        String name = scanner.nextLine();
        // Staff DAO functionality would search and display staff data by name
    }

    private void exitApp() {
        dbConfig.closeConnection();
        System.out.println("Exiting application. Goodbye!");
        System.exit(0);
    }

    // Inside the Main class

    private void editMenuItem() {
        MenuItemDAO menuItemDAO = new MenuItemDAO(connection);
        System.out.print("Enter the ID of the menu item to edit: ");
        int itemId = inputValidator.getValidatedInt();

        MenuItem menuItem = menuItemDAO.getMenuItemById(itemId);
        if (menuItem == null) {
            System.out.println("No menu item found with the provided ID.");
            return;
        }

        System.out.println("Editing Menu Item: " + menuItem.getItemName());
        System.out.print("Enter new name (or press Enter to keep '" + menuItem.getItemName() + "'): ");
        String newName = scanner.nextLine();
        if (newName.isEmpty()) newName = menuItem.getItemName();

        System.out.print("Enter new price (or press Enter to keep '" + menuItem.getPrice() + "'): ");
        double newPrice = inputValidator.getValidatedOptionalDoubleInput(menuItem.getPrice());

        System.out.print("Enter new category (or press Enter to keep '" + menuItem.getCategory() + "'): ");
        String newCategory = scanner.nextLine();
        if (newCategory.isEmpty()) newCategory = menuItem.getCategory();

        System.out.print("Enter new description (or press Enter to keep current description): ");
        String newDescription = scanner.nextLine();
        if (newDescription.isEmpty()) newDescription = menuItem.getDescription();

        System.out.print("Enter new ingredients (comma-separated) or press Enter to keep current ingredients: ");
        String ingredientsInput = scanner.nextLine();
        ArrayList<String> newIngredients = ingredientsInput.isEmpty()
                ? menuItem.getIngredients()
                : new ArrayList<>(List.of(ingredientsInput.split(",")));

        boolean updated = menuItemDAO.updateMenuItem(itemId, newName, newPrice, newCategory, newDescription, newIngredients);
        if (updated) {
            System.out.println("Menu item updated successfully.");
        } else {
            System.out.println("An error occurred while updating the menu item.");
        }
    }

    private void deleteMenuItem() {
        MenuItemDAO menuItemDAO = new MenuItemDAO(connection);
        System.out.print("Enter the ID of the menu item to delete: ");
        int itemId = inputValidator.getValidatedInt();

        MenuItem menuItem = menuItemDAO.getMenuItemById(itemId);
        if (menuItem == null) {
            System.out.println("No menu item found with the provided ID.");
            return;
        }

        System.out.println("Are you sure you want to delete the menu item '" + menuItem.getItemName() + "'? (yes/no)");
        String confirmation = scanner.nextLine();
        if (!confirmation.equalsIgnoreCase("yes")) {
            System.out.println("Delete operation canceled.");
            return;
        }

        boolean deleted = menuItemDAO.deleteMenuItem(itemId);
        if (deleted) {
            System.out.println("Menu item deleted successfully.");
        } else {
            System.out.println("An error occurred while deleting the menu item.");
        }
    }


}
