package com.coderscampus.assignment14.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.assignment14.domain.Channel;
import com.coderscampus.assignment14.domain.Message;
import com.coderscampus.assignment14.repository.MessageRepository;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository messageRepo;
    private final ChannelService channelService;

    @Autowired
    public MessageService(MessageRepository messageRepo, ChannelService channelService) {
        this.messageRepo = messageRepo;
        this.channelService = channelService;
    }

    public List<Message> findByChannel(Channel channel) {
        return messageRepo.findByChannel(channel);
    }

    public List<Message> findByChannelId(Long channelId) {
        return messageRepo.findByChannel_Id(channelId);
    }

    public Message save(Message message) {
        return messageRepo.save(message);
    }
}
