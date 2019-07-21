package it.discovery.order.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("products")
@Getter
@Setter
public class Product {
    private int id;

    private double price;
}
