package com.coderscampus.assignment14.rest;

import com.coderscampus.assignment14.domain.User;
import com.coderscampus.assignment14.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {
    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public User getUser(@RequestBody String name) {
        try {
            return userService.findByName(name).get(0);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Error: no such User with name: " + name + "\n" + e);
            return null;
        }
    }

    @PostMapping("/user")
    public User postUser(@RequestBody User user) {
        return userService.save(user);
    }

}
