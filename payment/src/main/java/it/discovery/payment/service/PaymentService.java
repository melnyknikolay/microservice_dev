package it.discovery.payment.service;

import it.discovery.event.NotificationCreatedEvent;
import it.discovery.event.OrderCompletedEvent;
import it.discovery.event.OrderPayedEvent;
import it.discovery.event.bus.EventBus;
import it.discovery.order.client.OrderFacade;
import it.discovery.order.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentProvider paymentProvider;

    private final EventBus eventBus;

    private final OrderFacade orderFacade;

    public void pay(int id) {
        System.out.println("Starting payment for order " + id);
        OrderDTO order = orderFacade.findById(id);
        if (order == null || order.isCancelled()) {
            //TODO add logging/error handling
            return;
        }
        System.out.println("Charging " + order.getAmount() + " from credit card " + order.getCustomer().getCardNumber());

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
