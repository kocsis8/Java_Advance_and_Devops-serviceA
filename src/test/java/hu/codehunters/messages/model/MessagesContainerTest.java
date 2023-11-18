package hu.codehunters.messages.model;

import hu.codehunters.messages.repository.MessagesContainer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class MessagesContainerTest {

    private static final String MESSAGE = """
            {
            "message": "test"
            }
            """;

    private static final String PATH = "/messages";

    @Autowired
    private MockMvc mvc;

    private MessagesContainer messagesContainer;

    @Test
    void testMessageContainerBySendingManyMessages() throws Exception {
        //Given
        int numberOfSendMassages = 10_000;
        //When
        for (int i = 0; i < numberOfSendMassages; i++) {
            mvc.perform(MockMvcRequestBuilders
                    .post(PATH)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(MESSAGE)
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated());
        }
        //Then

        mvc.perform(MockMvcRequestBuilders.get(PATH))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(numberOfSendMassages));
    }
}