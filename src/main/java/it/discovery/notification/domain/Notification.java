package it.discovery.notification.domain;

import it.discovery.monolith.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table
public class Notification extends BaseEntity {
	private String recipient;

	private String email;

	private String title;

	private String text;

	private LocalDateTime created = LocalDateTime.now();
}
