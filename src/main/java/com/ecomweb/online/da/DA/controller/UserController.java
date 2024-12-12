package com.ecomweb.online.da.DA.controller;

import org.springframework.web.bind.annotation.*;
import com.ecomweb.online.da.DA.model.User;
import com.ecomweb.online.da.DA.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * REST controller class for managing users.
 * Provides CRUD operations for user entities.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    /**
     * Service for managing user-related operations.
     */
    private final UserService userService;

    /**
     * Constructor-based dependency injection for UserService.
     *
     * @param userService The service for handling user-related logic.
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Retrieves all users.
     *
     * @return A list of all users.
     */
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Adds a new user.
     *
     * @param user The user object to be added.
     * @return The added user object.
     */
    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    /**
     * Updates an existing user.
     *
     * @param id          The ID of the user to be updated.
     * @param updatedUser The updated user details.
     * @return The updated user object.
     */
    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }

    /**
     * Deletes a user by ID.
     *
     * @param id The ID of the user to be deleted.
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }
}
