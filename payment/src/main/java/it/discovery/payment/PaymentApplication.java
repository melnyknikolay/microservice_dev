package it.discovery.payment;

import it.discovery.event.OrderCompletedEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@ComponentScan("it.discovery")
@EnableKafka
public class PaymentApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication.class, args);
    }

    @Bean
    public DefaultKafkaConsumerFactory consumerFactory() {
        JsonDeserializer<OrderCompletedEvent> deserializer = new JsonDeserializer<>(OrderCompletedEvent.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);

        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "kafka:9092");
        return new DefaultKafkaConsumerFactory(configProps,
                new StringDeserializer(), deserializer);
    }
}
