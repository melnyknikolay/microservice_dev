package it.discovery.notification.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table
public class Notification {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private LocalDateTime createdAt;

	@PrePersist
	public void prePersist() {
		createdAt = LocalDateTime.now();
	}
    private String recipient;

    private String email;

    private String title;

    private String text;

    private LocalDateTime created = LocalDateTime.now();
}
