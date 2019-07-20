package it.discovery.monolith.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ORDERS")
public class Order extends BaseEntity {
	@OneToMany
	private List<OrderItem> items;

	private LocalDateTime orderDate;

	@ManyToOne
	private Customer customer;

	private boolean payed;

	private boolean delivered;

	private boolean cancelled;

	private LocalDateTime deliveryDate;

	private double deliveryPrice;

	public double getAmount() {
		return items.stream().mapToDouble(item -> item.getBook().getPrice() * item.getNumber()).sum();
	}

	public void addItem(OrderItem item) {
		if (items == null) {
			items = new ArrayList<>();
		}
		items.add(item);
	}

}
