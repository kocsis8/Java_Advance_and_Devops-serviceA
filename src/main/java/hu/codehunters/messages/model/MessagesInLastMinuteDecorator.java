package hu.codehunters.messages.model;

import lombok.Getter;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
public class MessagesInLastMinuteDecorator {

    private static final int MINUTE_IN_SEC = 60;

    private static final String NON_ALPHAMERIC_CHARACTERS_REGEX = "[^a-zA-Z\\d:]";
    private final List<Message> messagesInLastMinute;

    public MessagesInLastMinuteDecorator(List<Message> messages, Instant from) {
        this.messagesInLastMinute = messages
                .stream()
                .filter(message -> message.getCreatedAt().plusSeconds(MINUTE_IN_SEC).isAfter(from))
                .collect(Collectors.toList());
    }

    public List<String> getAllStoredWords(){
        return messagesInLastMinute.stream()
                .map(Message::getMessage)
                .filter(Objects::nonNull)
                .filter(messageString -> !messageString.trim().isEmpty())
                .map(messageString -> messageString.split(NON_ALPHAMERIC_CHARACTERS_REGEX))
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
    }

    public int size(){
        return messagesInLastMinute.size();}
}
