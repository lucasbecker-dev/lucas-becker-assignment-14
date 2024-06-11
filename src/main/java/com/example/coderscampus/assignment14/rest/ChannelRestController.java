package com.example.coderscampus.assignment14.rest;

import com.example.coderscampus.assignment14.domain.Channel;
import com.example.coderscampus.assignment14.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChannelRestController {

    private final ChannelService channelService;

    @Autowired
    public ChannelRestController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @GetMapping("/channels")
    public List<Channel> getChannels() {
        return channelService.findAll();
    }
}

