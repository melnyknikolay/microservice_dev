package it.discovery.monolith.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

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
