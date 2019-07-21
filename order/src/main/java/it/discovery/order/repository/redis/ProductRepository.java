package it.discovery.order.repository.redis;

import it.discovery.order.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
