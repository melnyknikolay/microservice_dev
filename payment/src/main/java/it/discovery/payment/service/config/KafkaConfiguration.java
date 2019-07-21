package it.discovery.payment.service.config;

import it.discovery.event.OrderCompletedEvent;
import it.discovery.payment.service.PaymentService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

@Configuration
public class KafkaConfiguration {

    @Autowired
    private PaymentService paymentService;

    @KafkaListener(groupId = "payment.id", topics = "orders")
    public void onOrderCompleted(@Payload OrderCompletedEvent event) {
        paymentService.pay(event.getOrderId());
    }
}
