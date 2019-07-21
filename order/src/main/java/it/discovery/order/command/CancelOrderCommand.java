package it.discovery.order.command;

import lombok.Value;

@Value
public class CancelOrderCommand {
    private int orderId;
}
