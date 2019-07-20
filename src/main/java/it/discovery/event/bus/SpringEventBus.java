package it.discovery.event.bus;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpringEventBus implements EventBus {
    private final ApplicationContext applicationContext;

    @Override
    public void sendEvent(ApplicationEvent event) {
        applicationContext.publishEvent(event);
    }
}
