package it.discovery.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
@Setter
public class NotificationCreatedEvent extends ApplicationEvent {
    private String recipient;

    private String email;

    private String title;

    private String text;

    private LocalDateTime created = LocalDateTime.now();

    public NotificationCreatedEvent(Object source) {
        super(source);
    }
}
