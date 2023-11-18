package hu.codehunters.messages.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class Message {

    private final String message;

    private final Instant createdAt;

    @JsonCreator
    public Message(@JsonProperty("message") String message) {
        this.message = message;
        this.createdAt = Instant.now();
    }


    public Message(String message,Instant instant) {
        this.message = message;
        this.createdAt = instant;
    }

    public String getMessage() {
        return message;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return this.message.equals(message.message);
    }

    @Override
    public int hashCode() {
        return message.hashCode();
    }
}
