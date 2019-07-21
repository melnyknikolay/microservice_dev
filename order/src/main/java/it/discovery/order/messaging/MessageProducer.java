package it.discovery.order.messaging;

import it.discovery.event.OrderCompletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendEvent(OrderCompletedEvent orderCompletedEvent) {
        kafkaTemplate.send("orders", orderCompletedEvent);
    }
}
