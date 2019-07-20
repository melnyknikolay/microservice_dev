package it.discovery.order.dto;

import lombok.Data;

@Data
public class OrderDTO {
    private int id;

    private double amount;

    //TODO verify mapping
    private CustomerDTO customer;
}
