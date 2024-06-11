package com.coderscampus.assignment14.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.coderscampus.assignment14.domain.User;
import com.coderscampus.assignment14.service.ChannelService;
import com.coderscampus.assignment14.service.UserService;

@Controller
public class WelcomeWebController {

    private final UserService userService;
    private final ChannelService channelService;

    @Autowired
    public WelcomeWebController(UserService userService, ChannelService channelService) {
        this.userService = userService;
        this.channelService = channelService;
    }

    @GetMapping("/")
    public ModelAndView welcome() {
        ModelAndView modelAndView = new ModelAndView("welcome");
        modelAndView.addObject("channels", channelService.findAll());
        return modelAndView;
    }

    @PostMapping("/user")
    public String createUser(@RequestParam String name) {
        User user = new User();
        user.setName(name);
        userService.save(user);
        return "redirect:/channels";
    }
}
