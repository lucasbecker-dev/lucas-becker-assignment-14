package com.coderscampus.assignment14.service;

import com.coderscampus.assignment14.domain.Channel;
import com.coderscampus.assignment14.domain.Message;
import com.coderscampus.assignment14.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
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

    public List<Message> findByChannelId(Long channelId) {
        return messageRepo.findByChannel_Id(channelId);
    }

    public Message save(Message message) {
        try {
            return messageRepo.save(message);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: message cannot be null\n" + e);
            return null;
        } catch (OptimisticLockingFailureException e) {
            System.err.println(e.getLocalizedMessage());
            return null;
        }
    }
}
