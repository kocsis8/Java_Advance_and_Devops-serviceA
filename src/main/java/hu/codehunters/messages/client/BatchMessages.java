package hu.codehunters.messages.client;

import hu.codehunters.messages.model.Message;
import lombok.Getter;

import java.util.List;
@Getter
public class BatchMessages {

    List<Message> messages;
    public BatchMessages(List<Message> messages) {
        this.messages = messages;
    }
}
