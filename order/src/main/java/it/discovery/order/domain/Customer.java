package it.discovery.order.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table
public class Customer {
	@Id
	@GeneratedValue(generator="cust_seq")
	@SequenceGenerator(name="cust_seq",sequenceName="CUST_SEQ", allocationSize=1)
	private int id;

	private LocalDateTime createdAt;

	@PrePersist
	public void prePersist() {
		createdAt = LocalDateTime.now();
	}

	private String name;
	
	private String address;
	
	private String phone;
	
	private String email;
	
	private String cardNumber;
	
	private double balance;
	
	@OneToMany
	private List<Order> orders;

}
