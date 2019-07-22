package it.discovery.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private LocalDateTime createdAt;

    private int bookId;

    private double price;

    private int number;

    public OrderItem(int bookId, double price, int number) {
        this.bookId = bookId;
        this.price = price;
        this.number = number;
    }
}
