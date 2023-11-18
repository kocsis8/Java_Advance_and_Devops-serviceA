package hu.codehunters.messages.model;

import java.util.ArrayList;
import java.util.List;

public class MessagesContainer {

    List<Message> messages = new ArrayList<>();

    public void addMessage(Message message) {
        messages.add(message);
    }

    public List<Message> getMessages() {
        return messages;
    }
}
