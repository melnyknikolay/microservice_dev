package it.discovery.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
@AllArgsConstructor
public class OrderCompletedEvent {
    private int orderId;
}
