package com.restaurantapp.service;

import com.restaurantapp.model.MenuItem;
import java.util.ArrayList;

public class Order {
    private String orderId;
    private ArrayList<MenuItem> orderDetails;
    private String orderStatus;

    public Order(String orderId) {
        this.orderId = orderId;
        this.orderDetails = new ArrayList<>();
        this.orderStatus = "Pending";
    }

    public void addItem(MenuItem item) {
        orderDetails.add(item);
        System.out.println(item + " added to order.");
    }

    public void removeItem(MenuItem item) {
        orderDetails.remove(item);
        System.out.println(item + " removed from order.");
    }

    public void updateStatus(String status) {
        this.orderStatus = status;
        System.out.println("Order " + orderId + " status updated to: " + status);
    }

    // Method to calculate the total cost of the order
    public double calculateTotal() {
        double total = 0;
        for (MenuItem item : orderDetails) {
            total += item.getPrice();  // Assuming getPrice() is defined in MenuItem
        }
        return total;
    }

    // Method to display all items in the order
    public void getOrderDetails() {
        System.out.println("Order ID: " + orderId + " | Status: " + orderStatus);
        for (MenuItem item : orderDetails) {
            item.getDetails();  // Assuming getDetails() is defined in MenuItem
        }
    }

    // Getter for orderId
    public String getOrderId() {
        return orderId;
    }

    // Setter for orderId
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    // Getter for orderStatus
    public String getOrderStatus() {
        return orderStatus;
    }

    // Setter for orderStatus
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    // Getter for orderDetails
    public ArrayList<MenuItem> getOrderDetailsList() {
        return orderDetails;
    }
}
