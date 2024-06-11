package com.coderscampus.assignment14.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.coderscampus.assignment14.domain.Message;
import com.coderscampus.assignment14.service.ChannelService;
import com.coderscampus.assignment14.service.MessageService;

import java.util.List;

@RestController
public class MessageRestController {
    private final MessageService messageService;
    private final ChannelService channelService;

    @Autowired
    public MessageRestController(MessageService messageService, ChannelService channelService) {
        this.messageService = messageService;
        this.channelService = channelService;
    }

    @PostMapping("/message")
    public Message createMessage(@RequestBody Message message) {
        return messageService.save(message);
    }

    @GetMapping("/channels/{channelId}/messages")
    public List<Message> getMessages(@PathVariable Long channelId) {
        return messageService.findByChannel(channelService.findById(channelId));
    }
}