package com.ecomweb.online.da.DA.controller;

import org.springframework.web.bind.annotation.*;
import com.ecomweb.online.da.DA.model.User;
import com.ecomweb.online.da.DA.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User addUser(@RequestParam String name, @RequestParam String email, @RequestParam String role) {
        return userService.addUser(name, email, role);
    }
}
