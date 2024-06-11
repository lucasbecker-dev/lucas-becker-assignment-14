package com.example.coderscampus.assignment14.web;

import com.example.coderscampus.assignment14.domain.Message;
import com.example.coderscampus.assignment14.service.ChannelService;
import com.example.coderscampus.assignment14.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {
    private final MessageService messageService;
    private final ChannelService channelService;

    @Autowired
    public MessageController(MessageService messageService, ChannelService channelService) {
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