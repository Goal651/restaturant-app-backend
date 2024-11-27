package com.restaurantapp.service;

public class Table {
    private String tableId;
    private int seatingCapacity;

    public Table(String tableId, int seatingCapacity) {
        this.tableId = tableId;
        this.seatingCapacity = seatingCapacity;
    }

    // Getter for tableId
    public String getTableId() {
        return tableId;
    }

    // Setter for tableId
    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    // Getter for seatingCapacity
    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    // Setter for seatingCapacity
    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    // Method to display table details
    public String getTableDetails() {
        return "Table ID: " + tableId + " | Seating Capacity: " + seatingCapacity;
    }

    @Override
    public String toString() {
        return getTableDetails();
    }
}
