package hu.codehunters.messages.repository;

import hu.codehunters.messages.model.Message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MessagesContainer {

    List<Message> messages = new CopyOnWriteArrayList<>();

    public void addMessage(Message message) {
        messages.add(message);
    }

    public List<Message> getMessages() {
        return Collections.unmodifiableList(messages);
    }

    public List<Message> clearAll() {
        List<Message> copiedMessages = new LinkedList<>(messages);
        messages.removeAll(copiedMessages);
        return copiedMessages;
    }

    public void addMessages(List<Message> messages){this.messages.addAll(messages);}
}
