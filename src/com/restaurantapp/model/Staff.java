package com.restaurantapp.model;

public class Staff extends Person {
    private String employeeId;
    private String role;
    private double salary;

    // Constructor to initialize Staff object
    public Staff(String name, int age, String address, String employeeId, String role, double salary) {
        super(name, age, address);
        this.employeeId = employeeId;
        this.role = role;
        this.salary = salary;
    }

    // Getter for employeeId
    public String getEmployeeId() {
        return employeeId;
    }

    // Setter for employeeId
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    // Getter for role
    public String getRole() {
        return role;
    }

    // Setter for role
    public void setRole(String role) {
        this.role = role;
    }

    // Getter for salary
    public double getSalary() {
        return salary;
    }

    // Setter for salary
    public void setSalary(double salary) {
        this.salary = salary;
    }

    // Method to assign a job (role) to the staff
    public void assignJob(String role) {
        this.role = role;
        System.out.println(super.getName() + " is now a " + role);
    }

    // Overridden getDetails method to include Staff-specific details
    @Override
    public String getDetails() {
        return super.getDetails() + ", Role: " + role + ", Salary: " + salary;
    }

    // Getter for position (role)
    public String getPosition() {
        return this.role;
    }

    // Setter for position (role)
    public void setPosition(String position) {
        this.role = position;
    }
}
