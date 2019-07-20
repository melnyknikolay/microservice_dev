package it.discovery.event;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class OrderCompletedEvent extends ApplicationEvent {
    private int orderId;

    public OrderCompletedEvent(int orderId, Object source) {
        super(source);
        this.orderId = orderId;
    }
}
