package it.discovery.event;

import lombok.Value;

@Value
public class OrderCompletedEvent {
    private int orderId;
}
