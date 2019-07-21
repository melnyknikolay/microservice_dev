package it.discovery.payment.service.config;

import it.discovery.event.OrderCompletedEvent;
import it.discovery.payment.service.PaymentService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
public class KafkaConfiguration {

    @Autowired
    private PaymentService paymentService;

    @KafkaListener(groupId = "payment.id", topics = "orders")
    public void onOrderCompleted(ConsumerRecord<String, Object> consumerRecord) {
        System.out.println("Key " + consumerRecord.key());
        System.out.println("Value " + consumerRecord.value());
        //paymentService.pay(event.getOrderId());
    }
}
