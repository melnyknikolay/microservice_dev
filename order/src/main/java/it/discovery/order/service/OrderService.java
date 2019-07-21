package it.discovery.order.service;

import it.discovery.event.NotificationCreatedEvent;
import it.discovery.event.OrderCompletedEvent;
import it.discovery.event.OrderDeliveredEvent;
import it.discovery.event.OrderPayedEvent;
import it.discovery.event.bus.EventBus;
import it.discovery.order.domain.Order;
import it.discovery.order.domain.OrderItem;
import it.discovery.order.messaging.MessageProducer;
import it.discovery.order.repository.CustomerRepository;
import it.discovery.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final CustomerRepository customerRepository;

    private final MessageProducer messageProducer;

    private final EventBus eventBus;

    public void complete(int orderId) {
        orderRepository.findById(orderId).ifPresent(order -> {
            order.setCompleted(true);
            orderRepository.save(order);

            messageProducer.sendEvent(new OrderCompletedEvent(orderId));

            NotificationCreatedEvent notificationEvent = new NotificationCreatedEvent(this);
            notificationEvent.setEmail(order.getCustomer().getEmail());
            notificationEvent.setRecipient(order.getCustomer().getName());
            notificationEvent.setTitle("Order " + order.getId() + " is completed");
            notificationEvent.setText("Hi/n. Your order has been completed");

            eventBus.sendEvent(notificationEvent);
        });
    }

    public void cancel(int orderId) {
        orderRepository.findById(orderId).ifPresent(order -> {
            if (order != null) {
                order.setCancelled(true);
                orderRepository.save(order);

                NotificationCreatedEvent notification = new NotificationCreatedEvent(this);
                notification.setEmail(order.getCustomer().getEmail());
                notification.setRecipient(order.getCustomer().getName());
                notification.setTitle("Order " + order.getId() + " is canceled");
                notification.setText("Hi/n. Your order has been canceled");

                eventBus.sendEvent(notification);
            }
        });
    }

    public Order createOrder(int bookId, int number, int customerId, double price) {
        Order order = new Order();
        order.addItem(new OrderItem(bookId, price, number));
        order.setOrderDate(LocalDateTime.now());
        order.setCustomer(customerRepository.getOne(customerId));

        return order;
    }

    public void addBook(int orderId, int bookId, int number, double price) {
        orderRepository.findById(orderId).ifPresent(order -> {
            order.addItem(new OrderItem(bookId, price, number));
            orderRepository.save(order);
        });
    }

    public List<Order> findOrders() {
        return orderRepository.findAll();
    }

    public Order findOrderById(int orderId) {
        return orderRepository.getOne(orderId);
    }

    @EventListener
    public void onOrderPayed(OrderPayedEvent event) {
        int orderId = event.getOrderId();
        orderRepository.findById(orderId).ifPresent(order -> {
            order.setPayed(true);
            order.getCustomer().setBalance(order.getCustomer().getBalance() - order.getAmount());
            orderRepository.save(order);
        });
    }

    @EventListener
    public void onOrderDelivered(OrderDeliveredEvent event) {
        int orderId = event.getOrderId();
        orderRepository.findById(orderId).ifPresent(order -> {
            order.setDelivered(true);
            order.setDeliveryDate(event.getDeliveryDate());

            orderRepository.save(order);
        });
    }

}
