package com.example.coderscampus.assignment14.service;

import com.example.coderscampus.assignment14.domain.Channel;
import com.example.coderscampus.assignment14.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return channelRepo.findById(channelId).orElse(null);
    }
}
