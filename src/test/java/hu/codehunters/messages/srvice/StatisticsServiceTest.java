package hu.codehunters.messages.srvice;

import hu.codehunters.messages.model.CountedWord;
import hu.codehunters.messages.model.Message;
import hu.codehunters.messages.model.Statistics;
import hu.codehunters.messages.repository.MessagesContainer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatisticsServiceTest {

    private static final Comparator<CountedWord> COUNTED_WORD_COMPARATOR =
            (w1,w2) -> w2.getWord().compareTo(w1.getWord());

    @Mock
    private MessagesContainer messagesContainer;

    @InjectMocks
    private StatisticsService underTest;

    @Test
    void shouldUnderTestReturnCorrectlyCalculatedStatistics(){
        //Given
        when(messagesContainer.getMessages())
                .thenReturn(List.of(new Message("test message1"), new Message("test message2")));
        //When
        Statistics result = underTest.calculateStatistics(Instant.now());

        //Then
        Statistics expectedResult = Statistics.builder().
                numberOfMessages(2)
                .averageLengthOfMessages(6.67)
                .countedWords(new LinkedList<>(List.of(
                        new CountedWord("test", 2),
                        new CountedWord("message1", 1),
                        new CountedWord("message2", 1)
                ))).build();

        sortCountedWordListInStatistics(result);

        sortCountedWordListInStatistics(expectedResult);

        assertEquals(expectedResult,result);
    }

    private void sortCountedWordListInStatistics(Statistics result) {
        result.getCountedWords().sort(COUNTED_WORD_COMPARATOR);
    }
}