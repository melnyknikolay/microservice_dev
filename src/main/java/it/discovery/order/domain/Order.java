package it.discovery.order.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private LocalDateTime createdAt;

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

    private boolean completed;

    public double getAmount() {
        return items.stream().mapToDouble(item -> item.getPrice() * item.getNumber()).sum();
    }

    public void addItem(OrderItem item) {
        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(item);
    }

}
