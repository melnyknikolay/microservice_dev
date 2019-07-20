package it.discovery.event;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.springframework.context.ApplicationEvent;

@Getter
public class OrderPayedEvent extends ApplicationEvent {
    private int orderId;

    public OrderPayedEvent(int orderId, Object source) {
        super(source);
        this.orderId = orderId;
    }
}
