package com.example.application.chat;

import jakarta.annotation.Nullable;

public record Channel(
        // TODO This should be a domain primitive, but it's currently not supported https://github.com/vaadin/hilla/issues/2055
        ChannelId channelId,
        String name,
        @Nullable Message lastMessage
) {

    public Channel(String id, String name) {
        this(new ChannelId(id), name, null);
    }
}
