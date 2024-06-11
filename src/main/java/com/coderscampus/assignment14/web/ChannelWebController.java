package com.coderscampus.assignment14.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ChannelWebController {
    @GetMapping("/channel")
    public String getChannel() {
        return "redirect:/welcome";
    }

    @GetMapping("/channel/{channelId}")
    public String getChannelById(@PathVariable Long channelId) {
        return "channel";
    }
}
