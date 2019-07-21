package it.discovery.order.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.discovery.event.DomainEvent;
import it.discovery.event.OrderCompletedEvent;
import it.discovery.event.bus.EventBus;
import it.discovery.event.messaging.MessageProducer;
import it.discovery.order.command.CancelOrderCommand;
import it.discovery.order.command.CompletedOrderCommand;
import it.discovery.order.domain.Order;
import it.discovery.order.log.EventLog;
import it.discovery.order.repository.jpa.CustomerRepository;
import it.discovery.order.repository.jpa.EventLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final EventLogRepository eventLogRepository;

    private final CustomerRepository customerRepository;

    private final MessageProducer messageProducer;

    private final EventBus eventBus;

    private final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public void complete(int orderId) {
        Order order = new Order();
        CompletedOrderCommand cmd = new CompletedOrderCommand(orderId);
        List<DomainEvent> events = order.process(cmd);

        eventLogRepository.saveAll(events.stream()
                .map(event -> {
                    try {
                        return new EventLog(event.getClass().getName(),
                                orderId, OBJECT_MAPPER.writeValueAsString(event));
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList()));

        events.forEach(event -> messageProducer
                .sendEvent(new OrderCompletedEvent(orderId)));

//            NotificationCreatedEvent notificationEvent = new NotificationCreatedEvent(this);
//            notificationEvent.setEmail(order.getCustomer().getEmail());
//            notificationEvent.setRecipient(order.getCustomer().getName());
//            notificationEvent.setTitle("Order " + order.getId() + " is completed");
//            notificationEvent.setText("Hi/n. Your order has been completed");
//
//            eventBus.sendEvent(notificationEvent);
    }

    public void cancel(int orderId) {
        Order order = new Order();
        CancelOrderCommand cmd = new CancelOrderCommand(orderId);
        List<DomainEvent> events = order.process(cmd);

        events.forEach(event -> messageProducer
                .sendEvent(new OrderCompletedEvent(orderId)));
//            NotificationCreatedEvent notification = new NotificationCreatedEvent(this);
//            notification.setEmail(order.getCustomer().getEmail());
//            notification.setRecipient(order.getCustomer().getName());
//            notification.setTitle("Order " + order.getId() + " is canceled");
//            notification.setText("Hi/n. Your order has been canceled");
//
//            eventBus.sendEvent(notification);
    }

    public Order createOrder(int bookId, int number, int customerId, double price) {
//        Order order = new Order();
//        order.addItem(new OrderItem(bookId, price, number));
//        order.setOrderDate(LocalDateTime.now());
//        order.setCustomer(customerRepository.getOne(customerId));
//
//        orderRepository.save(order);
//
//        return order;
        return null;
    }

    public void addBook(int orderId, int bookId, int number, double price) {
//        orderRepository.findById(orderId).ifPresent(order -> {
//            order.addItem(new OrderItem(bookId, price, number));
//            orderRepository.save(order);
//        });
    }

//    @KafkaListener(groupId = "order.id", topics = "orders")
//    public void onOrderPayed(@Payload OrderPayedEvent event) {
//        int orderId = event.getOrderId();
//        orderRepository.findById(orderId).ifPresent(order -> {
//            order.setPayed(true);
//            order.getCustomer().setBalance(order.getCustomer().getBalance() - order.getAmount());
//            orderRepository.save(order);
//        });
//    }
//
//    @KafkaListener(groupId = "order.id", topics = "orders")
//    public void onOrderDelivered(@Payload OrderDeliveredEvent event) {
//        int orderId = event.getOrderId();
//        orderRepository.findById(orderId).ifPresent(order -> {
//            order.setDelivered(true);
//            order.setDeliveryDate(event.getDeliveryDate());
//
//            orderRepository.save(order);
//        });
//    }

}
