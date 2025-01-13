package com.restaurantapp.validator;

import java.util.Scanner;

public class InputValidator {
    private final Scanner scanner = new Scanner(System.in);

    public double getValidatedDouble() {
        double result;
        while (true) {
            if (scanner.hasNextDouble()) {
                result = scanner.nextDouble();
                if (result >= 0) break;
                System.out.println("Invalid input. Please enter a positive number:");
            } else {
                System.out.println("Invalid input. Please enter a valid number:");
                scanner.next();
            }
        }
        scanner.nextLine(); // Consume newline
        return result;
    }

    public int getValidatedInt() {
        int result;
        while (true) {
            if (scanner.hasNextInt()) {
                result = scanner.nextInt();
                if (result >= 0) break;
                System.out.println("Invalid input. Please enter a positive number:");
            } else {
                System.out.println("Invalid input. Please enter a valid number:");
                scanner.next();
            }
        }
        scanner.nextLine(); // Consume newline
        return result;
    }

    public String getValidatedString() {
        String result;
        while (true) {
            result = scanner.nextLine().trim();
            if (!result.isEmpty()) break;
            System.out.println("Invalid input. Please enter a valid string:");
        }
        return result;
    }

    public double getValidatedOptionalDoubleInput(double defaultValue) {
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) return defaultValue;
        try {
            double value = Double.parseDouble(input);
            if (value >= 0) return value;
            System.out.println("Invalid input. Keeping the current value: " + defaultValue);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Keeping the current value: " + defaultValue);
        }
        return defaultValue;
    }
}
