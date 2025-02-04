package com.ecomweb.online.da.DA.service;

import com.ecomweb.online.da.DA.model.User;
import com.ecomweb.online.da.DA.model.Order;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing user-related operations.
 */
@Service
public class UserService {

    // List to store user data.
    private final List<User> users = new ArrayList<>(List.of(
            new User(1, "Udit", "udit11@gmail.com", "Admin"),
            new User(2, "Yash", "yash03@gmail.com", "Seller"),
            new User(3, "Ron", "ron25@gmail.com", "User"),
            new User(4, "Jhonny", "jhonny12@gmail.com", "User")
    ));

    // ID tracker for new users.
    private int nextId = 5;

    /**
     * Retrieves all registered users.
     */
    public List<User> getAllUsers() {
        return users;
    }

    /**
     * Adds a new user to the system.
     */
    public User addUser(User user) {
        user.setId(nextId++);
        users.add(user);
        return user;
    }

    /**
     * Updates an existing user by ID.
     */
    public User updateUser(int id, User updatedUser) {
        for (User user : users) {
            if (user.getId() == id) {
                user.setName(updatedUser.getName());
                user.setEmail(updatedUser.getEmail());
                user.setRole(updatedUser.getRole());
                return user;
            }
        }
        throw new IllegalArgumentException("User with ID " + id + " not found.");
    }

    /**
     * Deletes a user by ID.
     */
    public void deleteUser(int id) {
        boolean removed = users.removeIf(user -> user.getId() == id);
        if (!removed) {
            throw new IllegalArgumentException("User with ID " + id + " not found.");
        }
    }

    /**
     * Returns a hardcoded user ID.
     */
    public int getUserId() {
        return 1;
    }

    /*
     * ================================
     * TESTING: Circular Dependency Code
     * Uncomment the code below to test the circular dependency error.
     *
     * When active, this autowires OrderService, which, together with the similar
     * dependency in OrderService, creates a circular dependency that causes the app to fail.
     *
     * After testing, I commented them out to ensure the app starts normally.
     * ================================
     */

    // @Autowired
    // private OrderService orderService;
    //
    // // New method that retrieves a recent order for the current user by delegating to OrderService.
    // public Order getRecentOrderForUser() {
    //     int userId = getUserId();
    //     return orderService.getRecentOrderForUser(String.valueOf(userId));
    // }
}
