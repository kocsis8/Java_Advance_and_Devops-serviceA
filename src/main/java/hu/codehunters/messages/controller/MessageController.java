package hu.codehunters.messages.controller;

import hu.codehunters.messages.model.Message;
import hu.codehunters.messages.repository.MessagesContainer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessageController {

    MessagesContainer messages = new MessagesContainer();

    @PostMapping(value = "/messages")
    public ResponseEntity<Void> addMessageToList(@RequestBody Message message) {

        messages.addMessage(message);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @GetMapping("/messages")
    public ResponseEntity<List<Message>> showMessages() {

        return ResponseEntity.ok(messages.getMessages());
    }
}
