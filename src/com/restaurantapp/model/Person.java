package com.restaurantapp.model;

public class Person {
    private String name;
    private int age;
    private String address;

    // Constructor to initialize the person's details
    public Person(String name, int age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    // Getter for the name field
    public String getName() {
        return name;
    }

    // Setter for the name field
    public void setName(String name) {
        this.name = name;
    }

    // Getter for the age field
    public int getAge() {
        return age;
    }

    // Setter for the age field
    public void setAge(int age) {
        this.age = age;
    }

    // Getter for the address field
    public String getAddress() {
        return address;
    }

    // Setter for the address field
    public void setAddress(String address) {
        this.address = address;
    }

    // Method to return a string representation of the person's details
    public String getDetails() {
        return "Name: " + name + ", Age: " + age + ", Address: " + address;
    }
}
