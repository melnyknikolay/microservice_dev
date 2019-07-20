package it.discovery.order.dto;

import lombok.Data;

@Data
public class CustomerDTO {
    private String name;

    private String email;

    private String cardNumber;

    private double balance;
}
