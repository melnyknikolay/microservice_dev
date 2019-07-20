package it.discovery.event;

import lombok.Value;
import org.springframework.context.ApplicationEvent;

@Value
public class OrderDeliveredEvent extends ApplicationEvent {
    private int orderId;
}
