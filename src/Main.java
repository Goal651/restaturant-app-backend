import com.restaurantapp.DAOS.CustomerDAO;
import com.restaurantapp.db.DBConfig;
import com.restaurantapp.model.Customer;
import com.restaurantapp.model.Staff;
import com.restaurantapp.model.MenuItem;
import com.restaurantapp.utils.Menu;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Menu menu = new Menu();
    private static final DBConfig dbConfig = new DBConfig();
    private static Connection connection;
    private final static CustomerDAO customerDAO = new CustomerDAO(connection);


    public static void main(String[] args) {
        // Initialize the database connection
        connection = dbConfig.connectToDb();
        if (connection == null) {
            System.err.println("Database connection failed. Exiting...");
            System.exit(1);
        }


        while (true) {
            displayMainMenu();
            int choice = getValidatedChoice(1, 10); // Updated to reflect more choices

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
                case 10 -> exitApp();
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMainMenu() {
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
        System.out.println("10. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getValidatedChoice(int min, int max) {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a number: ");
            scanner.next(); // Clear invalid input
        }
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        return (choice >= min && choice <= max) ? choice : -1;
    }

    private static void addCustomer() {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter customer age (must be a positive number): ");
        int age = getValidatedIntInput();
        System.out.print("Enter customer address: ");
        String address = scanner.nextLine();
        System.out.print("Enter customer ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter customer phone number: ");
        String phone = scanner.nextLine();

        Customer customer = new Customer(name, age, address, id, phone);
        customerDAO.addCustomer(customer);
        System.out.println("Customer added successfully!");
    }

    private static void addStaff() {
        System.out.print("Enter staff name: ");
        String name = scanner.nextLine();
        System.out.print("Enter staff age (must be a positive number): ");
        int age = getValidatedIntInput();
        System.out.print("Enter staff address: ");
        String address = scanner.nextLine();
        System.out.print("Enter staff role: ");
        String role = scanner.nextLine();
        System.out.print("Enter staff salary (must be a positive number): ");
        double salary = getValidatedDoubleInput();
        System.out.println("Enter employee id: ");
        String employeeID = scanner.nextLine();

        Staff staff = new Staff(name, age, address, employeeID, role, salary);
        // Staff DAO functionality would save the staff to the database here
        System.out.println("Staff added successfully!");
    }

    private static void addMenuItem() {
        System.out.print("Enter menu item name: ");
        String name = scanner.nextLine();
        System.out.print("Enter menu item price (must be positive): ");
        double price = getValidatedDoubleInput();
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

        MenuItem menuItem = new MenuItem(name, price, category, description, ingredients);
        menu.addItem(menuItem);
        System.out.println("Menu item added successfully!");
    }

    private static void placeOrder() {
        System.out.print("Enter customer ID: ");
        String customerId = scanner.nextLine();
        List<Customer> customers = customerDAO.getAllCustomers();
        Customer customer = customers.stream()
                .filter(c -> c.getId().equals(customerId))
                .findFirst()
                .orElse(null);

        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }

        System.out.print("Enter the item to order: ");
        String orderItem = scanner.nextLine();
        customer.placeOrder(orderItem);
        System.out.println("Order placed successfully!");
    }

    private static void viewMenu() {
        menu.getMenu();
    }

    private static void viewCustomers() {
        List<Customer> customers = customerDAO.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No customers available.");
        } else {
            customers.forEach(System.out::println);
        }
    }

    private static void viewStaff() {
        // Staff DAO functionality would retrieve and display staff data
    }

    private static void searchCustomerById() {
        System.out.print("Enter Customer ID: ");
        String id = scanner.nextLine();
        Customer customer = customerDAO.getCustomerById(id);
        if (customer == null) {
            System.out.println("No customer found with ID: " + id);
        } else {
            System.out.println(customer);
        }
    }

    private static void searchStaffByName() {
        System.out.print("Enter Staff Name: ");
        String name = scanner.nextLine();
        // Staff DAO functionality would search and display staff data by name
    }

    private static void exitApp() {
        dbConfig.closeConnection();
        System.out.println("Exiting application. Goodbye!");
        System.exit(0);
    }

    private static int getValidatedIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a valid number: ");
            scanner.next(); // Clear invalid input
        }
        int input = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        return input;
    }

    private static double getValidatedDoubleInput() {
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid input. Please enter a valid number: ");
            scanner.next(); // Clear invalid input
        }
        double input = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        return input;
    }
}
