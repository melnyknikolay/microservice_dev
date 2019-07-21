package it.discovery.event;

import lombok.*;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCompletedEvent implements DomainEvent {
    private int orderId;
}
