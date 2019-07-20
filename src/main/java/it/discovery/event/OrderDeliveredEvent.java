package it.discovery.event;

import lombok.Value;

@Value
public class OrderDeliveredEvent {
    private int orderId;
}
