package it.discovery.monolith.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.discovery.monolith.domain.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
	
}
