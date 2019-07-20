package it.discovery.event.bus;

import org.springframework.context.ApplicationEvent;

public interface EventBus {
    void sendEvent(ApplicationEvent event);
}
