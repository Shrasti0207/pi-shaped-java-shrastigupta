package com.pishaped.user.service.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    @GetMapping
    public List<String> getUsers() {
        return List.of("Alice", "Bob");
    }
}
