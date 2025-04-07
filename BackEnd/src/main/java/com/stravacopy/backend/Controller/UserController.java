package com.stravacopy.backend.Controller;

import com.stravacopy.backend.Model.User;
import com.stravacopy.backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users") // Base URL for user-related requests
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Simple endpoint to get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
