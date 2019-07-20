package it.discovery.event;

import lombok.Value;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Value
public class OrderDeliveredEvent extends ApplicationEvent {
    private int orderId;

    private LocalDateTime deliveryDate;
}
