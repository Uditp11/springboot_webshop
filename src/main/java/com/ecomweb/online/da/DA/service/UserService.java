package com.ecomweb.online.da.DA.service;

import org.springframework.stereotype.Service;
import com.ecomweb.online.da.DA.model.User;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private List<User> users = new ArrayList<>(List.of(
        new User(1, "Udit", "udit11@gmail.com", "Admin"),
        new User(2, "Ron", "ron25@gmail.com", "User"),
        new User(3, "Jhonny", "jhonny12@gmail.com", "User")
    ));
    private int nextId = 4;

    public List<User> getAllUsers() {
        return users;
    }

    public User addUser(String name, String email, String role) {
        User newUser = new User(nextId++, name, email, role);
        users.add(newUser);
        return newUser;
    }
}
