package it.discovery.order.service;

import it.discovery.order.OrderApplication;
import it.discovery.order.domain.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringJUnitConfig(OrderApplication.class)
public class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Test
    void createOrder_validParams_orderCreated() {
        int bookId = 1;
        int customerId = 1;
        Order order = orderService.createOrder(bookId, 1, customerId, 100);
        assertNotNull(order);
        assertEquals(customerId, order.getCustomer().getId());
        assertEquals(1, order.getItems().size());
        assertEquals(bookId, order.getItems().get(0).getBookId());
    }

    @Test
    void deliverOrder_validOrder_orderDelivered() {
    }

}
