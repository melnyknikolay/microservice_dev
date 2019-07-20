package it.discovery.event;

import lombok.Value;
import org.springframework.context.ApplicationEvent;

@Value
public class OrderCancelledEvent extends ApplicationEvent {
    private int orderId;
}
