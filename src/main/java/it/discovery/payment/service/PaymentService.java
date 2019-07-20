package it.discovery.payment.service;

import it.discovery.event.NotificationCreatedEvent;
import it.discovery.event.OrderCompletedEvent;
import it.discovery.event.OrderPayedEvent;
import it.discovery.event.bus.EventBus;
import it.discovery.monolith.domain.Order;
import it.discovery.monolith.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final OrderRepository orderRepository;

    private final PaymentProvider paymentProvider;

    private final EventBus eventBus;

    public void pay(int id) {
        Order order = null; // taken from order service
        System.out.println("Charging " + order.getAmount() + " from credit card " + order.getCustomer().getCardNumber());

        paymentProvider.charge(order);

        eventBus.sendEvent(new OrderPayedEvent(order.getId()));

        NotificationCreatedEvent notification = new NotificationCreatedEvent(this);
        notification.setEmail(order.getCustomer().getEmail());
        notification.setRecipient(order.getCustomer().getName());
        notification.setTitle("Order " + order.getId() + " was payed");
        notification.setText("Hi/n. Your order was payed successfully");

        eventBus.sendEvent(notification);
        System.out.println("Charging completed");
    }

    @EventListener
    public void onOrderCompleted(OrderCompletedEvent event) {
        pay(event.getOrderId());
    }


}
