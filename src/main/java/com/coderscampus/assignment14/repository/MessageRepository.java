package com.coderscampus.assignment14.repository;

import com.coderscampus.assignment14.domain.Channel;
import com.coderscampus.assignment14.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    public List<Message> findByChannel(Channel channel);

    public List<Message> findByChannel_Id(Long channelId);
}
