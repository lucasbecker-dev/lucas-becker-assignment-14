package com.coderscampus.assignment14.config;

import org.springframework.stereotype.Component;

import com.coderscampus.assignment14.domain.Channel;
import com.coderscampus.assignment14.repository.ChannelRepository;

import jakarta.annotation.PostConstruct;

@Component
public class ChannelLoader {
    private final ChannelRepository channelRepository;

    public ChannelLoader(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    @PostConstruct
    private void loadData() {
        channelRepository.save(Channel.builder().name("General").build());
        channelRepository.save(Channel.builder().name("Development").build());
        channelRepository.save(Channel.builder().name("Off-Topic").build());
    }
}
