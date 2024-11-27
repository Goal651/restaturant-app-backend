package com.restaurantapp.service;

import com.restaurantapp.model.Customer;

public class Reservation {
    private String reservationId;
    private Customer customer;
    private Table table;
    private String reservationTime;
    private String status;

    public Reservation(String reservationId, Customer customer, Table table, String reservationTime) {
        this.reservationId = reservationId;
        this.customer = customer;
        this.table = table;
        this.reservationTime = reservationTime;
        this.status = "Confirmed";
    }

    public void cancelReservation() {
        this.status = "Cancelled";
        System.out.println("Reservation " + reservationId + " cancelled.");
    }

    public void viewReservationDetails() {
        System.out.println("Reservation ID: " + reservationId + " | Customer: " + customer.getName() +
                " | Table: " + table + " | Time: " + reservationTime + " | Status: " + status);
    }

    // Getter for reservationId
    public String getReservationId() {
        return reservationId;
    }

    // Setter for reservationId
    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    // Getter for customer
    public Customer getCustomer() {
        return customer;
    }

    // Setter for customer
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    // Getter for table
    public Table getTable() {
        return table;
    }

    // Setter for table
    public void setTable(Table table) {
        this.table = table;
    }

    // Getter for reservationTime
    public String getReservationTime() {
        return reservationTime;
    }

    // Setter for reservationTime
    public void setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime;
    }

    // Getter for status
    public String getStatus() {
        return status;
    }

    // Setter for status
    public void setStatus(String status) {
        this.status = status;
    }
}
