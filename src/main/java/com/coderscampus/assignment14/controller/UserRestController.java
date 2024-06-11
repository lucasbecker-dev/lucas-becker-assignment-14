package com.coderscampus.assignment14.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.coderscampus.assignment14.domain.User;
import com.coderscampus.assignment14.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class UserRestController {
    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public User postUser(@RequestBody User user) {
        return userService.save(user);
    }

}
