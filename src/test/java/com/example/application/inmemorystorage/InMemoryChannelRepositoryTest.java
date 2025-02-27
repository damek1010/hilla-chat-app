package com.example.application.inmemorystorage;

import com.example.application.chat.Channel;
import com.example.application.chat.ChannelId;
import com.example.application.chat.Message;
import com.example.application.chat.MessageId;
import com.example.application.chat.spi.ChannelRepository;
import com.example.application.chat.spi.MessageRepository;
import com.example.application.chat.spi.NewChannel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class InMemoryChannelRepositoryTest {

    private ChannelRepository repo;
    private MessageRepository messageRepoMock;

    @BeforeEach
    public void setUp() {
        messageRepoMock = Mockito.mock(MessageRepository.class);
        repo = new InMemoryChannelRepository(messageRepoMock);
    }

    @Test
    void repository_is_empty_at_first() {
        assertThat(repo.findAll()).isEmpty();
        assertThat(repo.exists(new ChannelId("nonexistent"))).isFalse();
        assertThat(repo.findById(new ChannelId("nonexitent"))).isEmpty();
    }

    @Test
    void repository_can_save_and_retrieve_channels() {
        var channel1 = repo.save(new NewChannel("channel1"));
        var channel2 = repo.save(new NewChannel("channel2"));

        assertThat(repo.findAll()).containsExactly(channel1, channel2);
        assertThat(repo.exists(channel1.channelId())).isTrue();
        assertThat(repo.exists(channel2.channelId())).isTrue();
        assertThat(repo.findById(channel1.channelId())).contains(channel1);
        assertThat(repo.findById(channel2.channelId())).contains(channel2);
    }

    @Test
    void channels_are_sorted_by_name() {
        var channel1 = repo.save(new NewChannel("channel1"));
        var channel2 = repo.save(new NewChannel("channel2"));

        assertThat(repo.findAll()).containsExactly(channel1, channel2);
    }

    @Test
    void latest_message_is_included_when_retrieving_channels() {
        var channel1 = repo.save(new NewChannel("channel1"));
        var message = new Message(new MessageId("messageId"), channel1.channelId(), 1L, Instant.now(), "user", "message");
        when(messageRepoMock.findLatest(channel1.channelId(), 1)).thenReturn(List.of(message));
        assertThat(repo.findById(channel1.channelId())).contains(new Channel(channel1.channelId(), channel1.name(), message));
    }
}
