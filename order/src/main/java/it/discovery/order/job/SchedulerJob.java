package it.discovery.order.job;

import it.discovery.event.NotificationCreatedEvent;
import it.discovery.event.bus.EventBus;
import it.discovery.order.repository.redis.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchedulerJob {
    private final OrderRepository orderRepository;

    private final EventBus eventBus;

    @Scheduled(fixedDelay = 60000)
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
        orderRepository.findAll().stream().filter(order -> !order.isPayed()).forEach(order -> {
            NotificationCreatedEvent notification = new NotificationCreatedEvent(this);
            notification.setEmail(order.getCustomer().getEmail());
            notification.setRecipient(order.getCustomer().getName());
            notification.setTitle("Don't forget to pay order " + order.getId());
            notification.setText("Please, pay your outstaning order " + order.getId());

            eventBus.sendEvent(notification);
        });

    }

}
