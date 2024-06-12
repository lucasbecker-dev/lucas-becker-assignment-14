package com.coderscampus.assignment14.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeWebController {
    @GetMapping("/")
    public String getRoot() {
        return "redirect:/welcome";
    }

    @GetMapping("/welcome")
    public String getWelcome() {
        return "welcome";
    }
}
