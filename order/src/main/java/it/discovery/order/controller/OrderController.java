package it.discovery.order.controller;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import it.discovery.order.dto.OrderDTO;
import it.discovery.order.repository.redis.OrderRepository;
import it.discovery.order.service.OrderService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    private final Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    public void addBook(int orderId, int bookId, int number, double price) {
        orderService.addBook(orderId, bookId, number, price);
    }

    @PutMapping("{orderId}")
    public void completeOrder(@PathVariable int orderId) {
        orderService.complete(orderId);
    }

    public void cancel(int orderId) {
        orderService.cancel(orderId);
    }

    @GetMapping
    public List<OrderDTO> findOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/{id}")
    public OrderDTO findOrderById(@PathVariable int id) {
        return orderRepository.findById(id).orElse(null);
    }

    @PostMapping
    public int createOrder(@RequestParam int bookId, @RequestParam int number, @RequestParam int customerId,
                           @RequestParam double price) {
        return orderService.createOrder(bookId, number, customerId, price).getId();
    }
}
