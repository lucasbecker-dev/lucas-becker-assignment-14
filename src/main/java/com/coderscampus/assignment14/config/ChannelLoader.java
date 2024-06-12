package com.coderscampus.assignment14.config;

import com.coderscampus.assignment14.domain.Channel;
import com.coderscampus.assignment14.repository.ChannelRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChannelLoader {
    private final ChannelRepository channelRepo;

    @Autowired
    public ChannelLoader(ChannelRepository channelRepo) {
        this.channelRepo = channelRepo;
    }

    @PostConstruct
    private void loadData() {
        Channel general = new Channel();
        Channel development = new Channel();
        Channel offTopic = new Channel();

        general.setName("General");
        development.setName("Development");
        offTopic.setName("Off-Topic");

        channelRepo.save(general);
        channelRepo.save(development);
        channelRepo.save(offTopic);
    }
}
