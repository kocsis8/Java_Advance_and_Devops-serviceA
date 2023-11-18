package hu.codehunters.messages.srvice;

import hu.codehunters.messages.model.CountedWord;
import hu.codehunters.messages.model.MassagesInLastMinuteDecorator;
import hu.codehunters.messages.model.Statistics;
import hu.codehunters.messages.repository.MessagesContainer;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class StatisticsService {

    private final MessagesContainer messagesContainer;

    public StatisticsService(MessagesContainer messagesContainer) {
        this.messagesContainer = messagesContainer;
    }

    public Statistics calculateStatistics(Instant from) {
        MassagesInLastMinuteDecorator messages = new MassagesInLastMinuteDecorator(messagesContainer.getMessages(),from);

        int numberOfMessages = messages.size();

        double averageLengthOfMessages = getAverageLengthOfMessages(messages.getAllStoredWords());

        List<CountedWord> countedWords = countedWordsOccurrences(messages.getAllStoredWords());

        return new Statistics(numberOfMessages,averageLengthOfMessages,countedWords);
    }

    private List<CountedWord> countedWordsOccurrences(List<String> allStoredWords) {
        Map<String, Integer> ocourrencesagMap = new HashMap<>();

        for (String word : allStoredWords) {
            ocourrencesagMap.put(word, ocourrencesagMap.getOrDefault(word, 0) + 1);
        }

        List<CountedWord> countedWords = new LinkedList<>();

        for (Map.Entry<String, Integer> entry : ocourrencesagMap.entrySet()) {
            countedWords.add(new CountedWord(entry.getKey(),entry.getValue()));
        }
        return countedWords;
    }

    private double getAverageLengthOfMessages(List<String> messages) {
        double averageLengthOfMessages = messages.stream()
                .distinct()
                .mapToInt(String::length)
                .average()
                .orElse(0D);

        return roundDoubleValueTo2Digits(averageLengthOfMessages);
    }

    private double roundDoubleValueTo2Digits(double averageLengthOfMessages) {
        return new BigDecimal(averageLengthOfMessages).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
