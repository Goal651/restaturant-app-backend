package com.restaurantapp.utils;

import java.util.HashSet;
import java.util.Set;

public class Role {
    private final String roleName;  // Role name should not change after creation
    private Set<String> permissions;

    // Constructor to initialize the role and permissions
    public Role(String roleName, Set<String> permissions) {
        this.roleName = roleName;
        this.permissions = permissions != null ? permissions : new HashSet<>();
    }

    // Assign new permissions by replacing the existing ones
    public void assignPermissions(Set<String> newPermissions) {
        this.permissions = newPermissions;
        System.out.println("Permissions for " + roleName + " updated to: " + permissions);
    }

    // Add a single permission
    public void addPermission(String permission) {
        permissions.add(permission);
        System.out.println("Added permission: " + permission + " to " + roleName);
    }

    // Remove a permission
    public void removePermission(String permission) {
        permissions.remove(permission);
        System.out.println("Removed permission: " + permission + " from " + roleName);
    }

    // Get the current permissions
    public Set<String> getPermissions() {
        return permissions;
    }

    // Get the role name
    public String getRoleName() {
        return roleName;
    }

    // Display role details
    public void displayRoleDetails() {
        System.out.println("Role: " + roleName + " | Permissions: " + permissions);
    }
}
