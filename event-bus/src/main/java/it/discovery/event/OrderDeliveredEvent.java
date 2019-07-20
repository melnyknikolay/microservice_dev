package it.discovery.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderDeliveredEvent extends ApplicationEvent {
    private int orderId;

    private LocalDateTime deliveryDate;

    public OrderDeliveredEvent(int orderId, LocalDateTime deliveryDate, Object source) {
        super(source);
    }
}
