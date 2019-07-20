package it.discovery.event;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class NotificationCreatedEvent {
    private String recipient;

    private String email;

    private String title;

    private String text;

    private LocalDateTime created = LocalDateTime.now();
}
