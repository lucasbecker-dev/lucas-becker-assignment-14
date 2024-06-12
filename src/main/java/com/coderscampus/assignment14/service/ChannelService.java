package com.coderscampus.assignment14.service;

import com.coderscampus.assignment14.domain.Channel;
import com.coderscampus.assignment14.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelService {
    private final ChannelRepository channelRepo;

    @Autowired
    public ChannelService(ChannelRepository channelRepo) {
        this.channelRepo = channelRepo;
    }

    public List<Channel> findAll() {
        return channelRepo.findAll();
    }

    public Channel findById(Long channelId) {
        try {
            return channelRepo.findById(channelId).orElse(null);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: channelId cannot be null\n" + e);
            return null;
        }
    }

    public Channel save(Channel channel) {
        try {
            return channelRepo.save(channel);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: channel cannot be null\n" + e);
            return null;
        } catch (OptimisticLockingFailureException e) {
            System.err.println(e);
            return null;
        }
    }
}
