package it.discovery.order.repository.jpa;

import it.discovery.order.log.EventLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventLogRepository extends JpaRepository<EventLog, Integer> {
}
