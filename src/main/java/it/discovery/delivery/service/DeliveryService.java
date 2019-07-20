package it.discovery.delivery.service;

import it.discovery.event.NotificationCreatedEvent;
import it.discovery.event.OrderDeliveredEvent;
import it.discovery.event.OrderPayedEvent;
import it.discovery.event.bus.EventBus;
import it.discovery.monolith.domain.Notification;
import it.discovery.monolith.domain.Order;
import it.discovery.monolith.repository.OrderRepository;
import it.discovery.monolith.service.NotificationService;
import jdk.jfr.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DeliveryService {
	private final EventBus eventBus;
	
	public void deliver(int orderId) {
		//TODO use batching for orders delivery
		Order order = null; //
		System.out.println("Delivering order to customer ...");

		eventBus.sendEvent(new OrderDeliveredEvent(order.getId(), LocalDateTime.now()));
		
		NotificationCreatedEvent notification = new NotificationCreatedEvent(this);
		notification.setEmail(order.getCustomer().getEmail());
		notification.setRecipient(order.getCustomer().getName());
		notification.setTitle("Order " + order.getId() + " is delivered");
		notification.setText("Hi/n. Your order has been delivered");

		eventBus.sendEvent(notification);

		System.out.println("Order delivered!");
	}

	@EventListener
	public void onOrderPayed(OrderPayedEvent event) {
		deliver(event.getOrderId());
	}

}
