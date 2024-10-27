package com.example.application.chat.spi;

import com.example.application.chat.ChannelId;
import java.time.Instant;

public record NewMessage(ChannelId channelId, Instant timestamp, String author, String message) {
}
