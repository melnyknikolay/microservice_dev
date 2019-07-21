package it.discovery.order.repository.redis;

import it.discovery.order.domain.Order;
import it.discovery.order.dto.OrderDTO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<OrderDTO, Integer> {

    List<OrderDTO> findAll();
}
