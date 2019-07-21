package it.discovery.order.client;

import it.discovery.order.dto.OrderDTO;

import java.util.List;

public interface OrderFacade {
    List<OrderDTO> findAll();

    OrderDTO findById(int orderId);
}
