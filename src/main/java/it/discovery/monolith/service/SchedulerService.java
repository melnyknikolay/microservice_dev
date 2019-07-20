package it.discovery.monolith.service;

import it.discovery.event.NotificationCreatedEvent;
import it.discovery.event.bus.EventBus;
import it.discovery.monolith.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchedulerService {
    private final OrderRepository orderRepository;

    private final EventBus eventBus;

    public void run() {
        notifyPendingOrders();
//		startDeliveries();
    }

//	private void startDeliveries() {
//		while (true) {
//			try {
//				orderRepository.findAll().stream().filter(order -> order.isPayed() && !order.isDelivered())
//						.forEach(order -> {
//							deliveryService.deliver(order);
//						});
//				// Activate each 30 seconds
//				Thread.sleep(30 * 60 * 1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//	}

    private void notifyPendingOrders() {
        while (true) {
            try {
                orderRepository.findAll().stream().filter(order -> !order.isPayed()).forEach(order -> {
                    NotificationCreatedEvent notification = new NotificationCreatedEvent(this);
                    notification.setEmail(order.getCustomer().getEmail());
                    notification.setRecipient(order.getCustomer().getName());
                    notification.setTitle("Don't forget to pay order " + order.getId());
                    notification.setText("Please, pay your outstaning order " + order.getId());

                    eventBus.sendEvent(notification);
                });
                // Activate each 30 seconds
                Thread.sleep(30 * 60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
