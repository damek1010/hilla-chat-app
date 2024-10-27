package com.example.application.chat;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public record MessageId(String id) {
    @JsonCreator
    public MessageId {
    }

    @JsonValue
    public String getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return id;
    }
}
