package com.example.application.chat;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public record ChannelId(String id) {
    @JsonCreator
    public ChannelId {
    }

    @JsonValue
    public String getValue() {
        return this.id;
    }

    @Override
    public String toString() {
        return id;
    }
}
