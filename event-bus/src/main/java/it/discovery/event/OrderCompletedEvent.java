package it.discovery.event;

import lombok.*;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCompletedEvent {
    private int orderId;
}
