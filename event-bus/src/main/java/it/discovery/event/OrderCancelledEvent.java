package it.discovery.event;

import lombok.Getter;
import lombok.Value;
import org.springframework.context.ApplicationEvent;

@Getter
public class OrderCancelledEvent extends ApplicationEvent {
    private int orderId;

    public OrderCancelledEvent(int orderId, Object source) {
        super(source);
        this.orderId = orderId;
    }
}
