package it.discovery.payment.service;

import it.discovery.event.NotificationCreatedEvent;
import it.discovery.event.OrderCompletedEvent;
import it.discovery.event.bus.EventBus;
import it.discovery.monolith.domain.Order;
import it.discovery.monolith.repository.OrderRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private OrderRepository orderRepository;

    private PaymentProvider paymentProvider;

    private EventBus eventBus;

    public void pay(Order order) {
        System.out.println("Charging " + order.getAmount() + " from credit card " + order.getCustomer().getCardNumber());

        paymentProvider.charge(order);

        order.setPayed(true);
        orderRepository.save(order);

        NotificationCreatedEvent notification = new NotificationCreatedEvent(this);
        notification.setEmail(order.getCustomer().getEmail());
        notification.setRecipient(order.getCustomer().getName());
        notification.setTitle("Order " + order.getId() + " was payed");
        notification.setText("Hi/n. Your order was payed successfully");

        eventBus.sendEvent(notification);
        System.out.println("Charging completed");
    }

    @EventListener
    public void onNotificationEvent(OrderCompletedEvent event) {
        pay(null);
    }


}
