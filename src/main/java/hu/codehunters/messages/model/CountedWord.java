package hu.codehunters.messages.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;


@Value
@AllArgsConstructor
@Builder
public class CountedWord {
    @JsonProperty(value = "word")
    String word;
    @JsonProperty(value = "count")
    int occurrence;
}
