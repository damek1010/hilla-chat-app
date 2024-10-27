package com.example.application.chat.spi;

import com.example.application.chat.Channel;

import com.example.application.chat.ChannelId;
import java.util.List;
import java.util.Optional;

public interface ChannelRepository {

    List<Channel> findAll();

    Channel save(NewChannel newChannel);

    Optional<Channel> findById(ChannelId channelId);

    boolean exists(ChannelId channelId);
}
