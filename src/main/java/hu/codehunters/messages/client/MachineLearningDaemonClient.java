package hu.codehunters.messages.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(
        value = "machineLearningClient",
        url = "http://localhost:8181"
)
public interface MachineLearningDaemonClient {

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/mock_external_service",
            produces = "application/json"
    )
    ResponseEntity<String> sendMessages(BatchMessages batchMessages);


}
