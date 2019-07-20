package it.discovery.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class OrderItem {
    private int bookId;

    private double price;

    private int number;
}
