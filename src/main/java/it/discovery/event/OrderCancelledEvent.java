package it.discovery.event;

import lombok.Value;

@Value
public class OrderCancelledEvent {
    private int orderId;
}
