package com.ecomweb.online.da.DA.model;

/**
 * Represents a user in the e-commerce system.
 * Contains essential user details such as ID, name, email, and role.
 */
public class User {

    /**
     * Unique identifier for the user.
     */
    private int id;

    /**
     * The user's full name.
     */
    private String name;

    /**
     * The user's email address.
     */
    private String email;

    /**
     * The user's role (e.g., admin, customer).
     */
    private String role;

    /**
     * Constructs a User with specified details.
     *
     * @param id    The user's unique identifier.
     * @param name  The user's name.
     * @param email The user's email address.
     * @param role  The user's role.
     */
    public User(int id, String name, String email, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    /**
     * Retrieves the user's ID.
     *
     * @return The user's ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the user's ID.
     *
     * @param id The new ID value.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the user's name.
     *
     * @return The user's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user's name.
     *
     * @param name The new name value.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the user's email address.
     *
     * @return The user's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email address.
     *
     * @param email The new email value.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the user's role.
     *
     * @return The user's role.
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the user's role.
     *
     * @param role The new role value.
     */
    public void setRole(String role) {
        this.role = role;
    }
}
