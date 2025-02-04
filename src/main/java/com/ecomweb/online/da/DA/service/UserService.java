package com.ecomweb.online.da.DA.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.ecomweb.online.da.DA.model.User;

/**
 * Service class for managing user-related operations.
 * Provides methods for retrieving, adding, updating, and deleting users.
 */
@Service
public class UserService {

    /**
     * List to store user data.
     */
    private final List<User> users = new ArrayList<>(List.of(
            new User(1, "Udit", "udit11@gmail.com", "Admin"),
            new User(2, "Yash", "yash03@gmail.com", "Seller"),
            new User(3, "Ron", "ron25@gmail.com", "User"),
            new User(4, "Jhonny", "jhonny12@gmail.com", "User")
    ));

    /**
     * ID tracker for new users.
     */
    private int nextId = 5;

    /**
     * Retrieves all registered users.
     *
     * @return A list of all users.
     */
    public List<User> getAllUsers() {
        return users;
    }

    /**
     * Adds a new user to the system.
     *
     * @param user The user to be added.
     * @return The added user with an assigned ID.
     */
    public User addUser(User user) {
        user.setId(nextId++);
        users.add(user);
        return user;
    }

    /**
     * Updates an existing user by ID.
     *
     * @param id           The ID of the user to update.
     * @param updatedUser  The updated user details.
     * @return The updated user.
     * @throws IllegalArgumentException if the user is not found.
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
     *
     * @param id The ID of the user to delete.
     * @throws IllegalArgumentException if the user is not found.
     */
    public void deleteUser(int id) {
        boolean removed = users.removeIf(user -> user.getId() == id);
        if (!removed) {
            throw new IllegalArgumentException("User with ID " + id + " not found.");
        }
    }

    public int getUserId() {
        return 1;
    }
}
