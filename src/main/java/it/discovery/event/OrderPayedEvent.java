package it.discovery.event;

import lombok.Value;

@Value
public class OrderPayedEvent {
    private int orderId;
}
