package com.coderscampus.assignment14.rest;

import com.coderscampus.assignment14.domain.Message;
import com.coderscampus.assignment14.domain.User;
import com.coderscampus.assignment14.service.ChannelService;
import com.coderscampus.assignment14.service.MessageService;
import com.coderscampus.assignment14.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        try {
            Message newMessage = new Message();
            User user = null;
            List<User> users = userService.findByName(message.get("userName"));
            if (users == null || users.isEmpty()) {
                user = new User();
                user.setName(message.get("userName"));
                userService.save(user);
            } else {
                user = users.get(0);
            }
            newMessage.setContent(message.get("content"));
            newMessage.setUser(user);
            newMessage.setChannel(channelService.findById(Long.parseLong(message.get("channelId"))));
            return messageService.save(newMessage);
        } catch (NumberFormatException e) {
            System.err.println("Error: invalid channelId: " + message.get("channelId") + '\n' + e);
            return null;
        } catch (ClassCastException | NullPointerException e) {
            System.err.println("Error: " + e);
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