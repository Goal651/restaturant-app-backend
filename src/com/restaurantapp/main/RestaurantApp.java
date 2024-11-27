package com.restaurantapp.main;

import com.restaurantapp.model.Customer;
import com.restaurantapp.model.Staff;

import java.util.ArrayList;

public class RestaurantApp {
    private ArrayList<Staff> staffList;
    private ArrayList<Customer> customerList;

    public RestaurantApp() {
        staffList = new ArrayList<>();
        customerList = new ArrayList<>();
    }

    public void addStaff(Staff staff) {
        // Check if the staff already exists (based on name or employeeId)
        if (staffList.stream().anyMatch(existingStaff -> existingStaff.getName().equalsIgnoreCase(staff.getName()))) {
            System.out.println("Staff with the same name already exists.");
        } else {
            staffList.add(staff);
            System.out.println("Staff added successfully!");
        }
    }

    public void addCustomer(Customer customer) {
        // Check if the customer already exists (based on customerId or name)
        if (customerList.stream().anyMatch(existingCustomer -> existingCustomer.getName().equalsIgnoreCase(customer.getName()))) {
            System.out.println("Customer with the same name already exists.");
        } else {
            customerList.add(customer);
            System.out.println("Customer added successfully!");
        }
    }

    public void showStaff() {
        if (staffList.isEmpty()) {
            System.out.println("No staff available.");
            return;
        }
        System.out.println("Staff List:");
        for (Staff staff : staffList) {
            System.out.println(staff.getDetails());
        }
    }

    public void showCustomers() {
        if (customerList.isEmpty()) {
            System.out.println("No customers available.");
            return;
        }
        System.out.println("Customer List:");
        for (Customer customer : customerList) {
            System.out.println(customer.getDetails() + " | Order History: " + customer.getOrderHistory());
        }
    }

    public void assignJob(String staffName, String newJob) {
        for (Staff staff : staffList) {
            if (staff.getName().equalsIgnoreCase(staffName)) {
                staff.setPosition(newJob);
                System.out.println("Job updated for " + staff.getName() + " to " + newJob);
                return;
            }
        }
        System.out.println("Staff not found!");
    }
}
