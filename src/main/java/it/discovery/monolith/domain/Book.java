package it.discovery.monolith.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table
public class Book extends BaseEntity {
	private String name;
	
	private int year;
	
	private int pages;
	
	private double price;
	
	@OneToOne
	private Person author;
}
