package com.example.application.chat.spi;

import com.example.application.chat.ChannelId;
import com.example.application.chat.Message;
import com.example.application.chat.MessageId;
import jakarta.annotation.Nullable;

import java.util.List;

public interface MessageRepository {
    List<Message> findLatest(ChannelId channelId, int fetchMax, @Nullable MessageId lastSeenMessageId);

    default List<Message> findLatest(ChannelId channelId, int fetchMax) {
        return findLatest(channelId, fetchMax, null);
    }

    Message save(NewMessage newMessage);
}
