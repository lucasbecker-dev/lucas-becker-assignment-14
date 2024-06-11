package com.coderscampus.assignment14.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderscampus.assignment14.domain.Channel;
import com.coderscampus.assignment14.repository.ChannelRepository;

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
        return channelRepo.findById(channelId).orElse(null);
    }

    public Channel save(Channel channel) {
        return channelRepo.save(channel);
    }
}