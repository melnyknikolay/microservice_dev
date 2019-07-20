package it.discovery.monolith.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.discovery.monolith.domain.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
	
}
