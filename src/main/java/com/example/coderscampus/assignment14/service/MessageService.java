package com.example.coderscampus.assignment14.service;

import com.example.coderscampus.assignment14.domain.Channel;
import com.example.coderscampus.assignment14.domain.Message;
import com.example.coderscampus.assignment14.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepo;

    @Autowired
    public MessageService(MessageRepository messageRepo) {
        this.messageRepo = messageRepo;
    }

    public List<Message> findByChannel(Channel channel) {
        return messageRepo.findByChannel(channel);
    }

    public Message save(Message message) {
        return messageRepo.save(message);
    }
}
