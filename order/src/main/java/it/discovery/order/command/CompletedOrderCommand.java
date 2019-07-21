package it.discovery.order.command;

import lombok.Value;

@Value
public class CompletedOrderCommand {
    private int orderId;
}
