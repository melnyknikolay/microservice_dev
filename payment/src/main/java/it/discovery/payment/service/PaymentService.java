package it.discovery.payment.service;

import it.discovery.event.NotificationCreatedEvent;
import it.discovery.event.OrderCompletedEvent;
import it.discovery.event.OrderPayedEvent;
import it.discovery.event.bus.EventBus;
import it.discovery.order.dto.CustomerDTO;
import it.discovery.order.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentProvider paymentProvider;

    private final EventBus eventBus;

    public void pay(int id) {
        System.out.println("Starting payment for order " + id);
        OrderDTO order = null; // taken from order service
        CustomerDTO customer = null;
        System.out.println("Charging " + order.getAmount() + " from credit card " + customer.getCardNumber());

        paymentProvider.charge(order);

        eventBus.sendEvent(new OrderPayedEvent(order.getId(), this));

        NotificationCreatedEvent notification = new NotificationCreatedEvent(this);
        notification.setEmail(order.getCustomer().getEmail());
        notification.setRecipient(order.getCustomer().getName());
        notification.setTitle("Order " + order.getId() + " was payed");
        notification.setText("Hi/n. Your order was payed successfully");

        eventBus.sendEvent(notification);
        System.out.println("Charging completed");
    }

    @KafkaListener(topics = "orders")
    public void onOrderCompleted(OrderCompletedEvent event) {
        pay(event.getOrderId());
    }
}
