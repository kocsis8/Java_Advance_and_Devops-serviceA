package hu.codehunters.messages.model;



import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor
@Builder
public class Statistics {
    @JsonProperty(value = "posted_massages")
    Integer numberOfMessages;
    @JsonProperty(value = "average_length")
    Double averageLengthOfMessages;
    @JsonProperty(value = "occurrences")
    List<CountedWord> countedWords;


}
