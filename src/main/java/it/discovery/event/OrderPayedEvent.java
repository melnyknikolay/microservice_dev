package it.discovery.event;

import lombok.Value;
import org.springframework.context.ApplicationEvent;

@Value
public class OrderPayedEvent extends ApplicationEvent {
    private int orderId;
}
