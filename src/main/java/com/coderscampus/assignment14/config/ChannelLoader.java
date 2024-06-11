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
        Channel general = new Channel();
        Channel development = new Channel();
        Channel offTopic = new Channel();

        general.setName("General");
        development.setName("Development");
        offTopic.setName("Off-Topic");

        channelRepository.save(general);
        channelRepository.save(development);
        channelRepository.save(offTopic);
    }
}
