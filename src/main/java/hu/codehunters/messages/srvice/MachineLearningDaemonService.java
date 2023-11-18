package hu.codehunters.messages.srvice;

import hu.codehunters.messages.client.BatchMessages;
import hu.codehunters.messages.client.MachineLearningDaemonClient;
import hu.codehunters.messages.model.Message;
import hu.codehunters.messages.repository.MessagesContainer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MachineLearningDaemonService {

    private final MachineLearningDaemonClient client;

    private final MessagesContainer messagesContainer;

    public void sendMassagesToMachineLearningExternalApi(){
        List<Message> messages = messagesContainer.clearAll();
        BatchMessages batchMessages = new BatchMessages(messages);

        try{
            ResponseEntity<String> response = client.sendMessages(batchMessages);
            if (response.getStatusCode() != HttpStatus.OK) {
                throw new RuntimeException("Machine learning daemon returned status code "+response.getStatusCode());
            }
        } catch (Exception e){
            messagesContainer.addMessages(messages);
        }
    }

    @Scheduled(cron = "0 0 23 * * ?")
    public void sendMassagesToMachineLearningExternalApiRegularly(){sendMassagesToMachineLearningExternalApi();}
}
