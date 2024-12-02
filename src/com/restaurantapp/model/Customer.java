package com.restaurantapp.model;

public class Customer extends Person {
    private String phoneNumber;
    private String orderHistory;

    // Constructor
    public Customer(String name, int age, String address,  String phoneNumber) {
        super(name, age, address);
        this.phoneNumber = phoneNumber;
        this.orderHistory = "";
    }




    // Getter for phoneNumber
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Setter for phoneNumber
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    // Getter for orderHistory
    public String getOrderHistory() {
        return orderHistory;
    }

    // Setter for orderHistory
    public void setOrderHistory(String orderHistory) {
        this.orderHistory = orderHistory;
    }

    // Method to place an order
    public void placeOrder(String orderDetails) {
        this.orderHistory += orderDetails + "\n";
        System.out.println(super.getName() + " placed an order: " + orderDetails);
    }

    // Method to view order history
    public void viewOrderHistory() {
        System.out.println("Order History for " + super.getName() + ":");
        System.out.println(orderHistory.isEmpty() ? "No orders placed yet." : orderHistory);
    }

    // Overridden getDetails method to include Customer-specific details
    @Override
    public String getDetails() {
        return super.getDetails() + ", Phone: " + phoneNumber;
    }

    // Example method for getting a specific order (could be expanded based on how you track orders)
    public void getOrder() {
        System.out.println("Fetching orders for " + super.getName());
    }

    // Overridden toString method
    @Override
    public String toString() {
        return "Customer{" +
                "Name='" + super.getName() + '\'' +
                ", Age=" + super.getAge() +
                ", Address='" + super.getAddress() + '\'' +
                ", Phone Number='" + phoneNumber + '\'' +
                ", Order History='" + (orderHistory.isEmpty() ? "No orders yet." : orderHistory.trim()) + '\'' +
                '}';
    }
}
