package hu.codehunters.messages.model;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MassagesInLastMinuteDecoratorTest {


    @Test
    void shouldMassagesInLastMinuteDecoratorCorrectly(){
        //Given
        Message message1 = new Message("test message1",  getInstant(20,0));
        Message message2 = new Message("test message2",  getInstant(23,15));

        //When
        MessagesInLastMinuteDecorator underTest = new MessagesInLastMinuteDecorator(
                List.of(message1,message2),
                getInstant(24,14));

        //Then
        assertAll(
                () -> assertEquals(1, underTest.size()),
                () -> assertEquals(List.of(message2), underTest.getMessagesInLastMinute()),
                () -> assertEquals(List.of("test", "message2"), underTest.getAllStoredWords())
        );
    }

    private Instant getInstant(int minute, int sec) {
        return LocalDateTime.of(2023,11,18,12,minute,sec).toInstant(ZoneOffset.UTC);
    }

}