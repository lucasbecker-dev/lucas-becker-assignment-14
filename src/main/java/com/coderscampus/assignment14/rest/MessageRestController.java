package com.coderscampus.assignment14.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.coderscampus.assignment14.domain.Message;
import com.coderscampus.assignment14.service.ChannelService;
import com.coderscampus.assignment14.service.MessageService;
import com.coderscampus.assignment14.service.UserService;

import java.util.List;
import java.util.Map;

@RestController
public class MessageRestController {
    private final MessageService messageService;
    private final ChannelService channelService;
    private final UserService userService;

    @Autowired
    public MessageRestController(MessageService messageService, ChannelService channelService,
            UserService userService) {
        this.messageService = messageService;
        this.channelService = channelService;
        this.userService = userService;
    }

    @PostMapping("/message")
    public Message createMessage(@RequestBody Map<String, String> message) {
        System.out.println(message);
        Message newMessage = null;
        try {
            newMessage = Message
                    .builder()
                    .content(message.get("content"))
                    .user(userService.findByName(message.get("userName")).get(0))
                    .channel(channelService.findById(Long.parseLong(message.get("channelId"))))
                    .build();
            return messageService.save(newMessage);
        } catch (NumberFormatException e) {
            System.err.println("Error: invalid channelId: " + message.get("channelId") + '\n' + e);
            return null;
        } catch (ClassCastException e) {
            System.err.println(e);
            return null;
        } catch (NullPointerException e) {
            System.err.println(e);
            return null;
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Error: no existing User with name: " + message.get("userName") + '\n' + e);
            return null;
        }
    }

    @GetMapping("/channels/{channelId}/messages")
    public List<Message> getMessages(@PathVariable Long channelId) {
        return messageService.findByChannelId(channelId);
    }
}