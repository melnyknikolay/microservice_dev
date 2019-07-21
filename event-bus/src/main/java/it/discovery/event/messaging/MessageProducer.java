package it.discovery.event.messaging;

import it.discovery.event.OrderCompletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
@RequiredArgsConstructor
public class MessageProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendEvent(Object event) {
        ListenableFuture future = kafkaTemplate.send("orders", event);
        future.addCallback(success -> System.out.println("Success"), err -> System.err.println("Error: " + err));
    }
}
