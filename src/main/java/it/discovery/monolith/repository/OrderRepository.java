package it.discovery.monolith.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.discovery.monolith.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{
}
