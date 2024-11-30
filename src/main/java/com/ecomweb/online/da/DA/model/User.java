package com.ecomweb.online.da.DA.model;

public class User {
    private int id;
    private final String name;
    private final String email;
    private final String role;

    public User(int id, String name, String email, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public String getEmail() { return email; }

    public String getRole() { return role; }
}