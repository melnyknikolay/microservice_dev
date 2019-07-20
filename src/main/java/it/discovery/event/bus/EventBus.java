package it.discovery.event.bus;

import it.discovery.event.DomainEvent;
import org.springframework.context.ApplicationEvent;

public interface EventBus {
    void sendEvent(ApplicationEvent event);
}
