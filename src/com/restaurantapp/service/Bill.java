package com.restaurantapp.service;

import com.restaurantapp.model.MenuItem;

public class Bill {
    private String billId;
    private Order order;
    private double totalAmount;
    private double tax;
    private double tip;

    public Bill(String billId, Order order, double tax, double tip) {
        this.billId = billId;
        this.order = order;
        this.tax = tax;
        this.tip = tip;
        calculateTotal();  // Automatically calculate total when Bill is created
    }

    public void generateBill() {
        System.out.println("Generating Bill ID: " + billId);
        order.getOrderDetails();  // Print order details
        System.out.println("Tax: " + tax + " | Tip: " + tip);
        System.out.println("Total Amount: " + totalAmount);
    }

    private void calculateTotal() {
        totalAmount = order.calculateTotal() + tax + tip;  // Use Order's calculateTotal method
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
