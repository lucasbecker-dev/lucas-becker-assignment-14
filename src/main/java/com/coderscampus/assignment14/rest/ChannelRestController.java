package com.coderscampus.assignment14.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.coderscampus.assignment14.domain.Channel;
import com.coderscampus.assignment14.service.ChannelService;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    @GetMapping("/channels/{channelId}")
    public Channel getChannelById(@PathVariable Long channelId) {
        return channelService.findById(channelId);
    }

    @PostMapping("/channels")
    public List<Channel> postChannels(@RequestBody List<Channel> channels) {
        List<Channel> createdChannels = new ArrayList<>();
        channels.forEach((channel) -> {
            createdChannels.add(channelService.save(channel));
        });
        return createdChannels;
    }

}
