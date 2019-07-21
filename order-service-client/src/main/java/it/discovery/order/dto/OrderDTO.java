package it.discovery.order.dto;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash("orders")
public class OrderDTO {
    private int id;

    private double amount;

    //TODO verify mapping
    private CustomerDTO customer;

    private boolean cancelled;

    private boolean completed;
}
