package it.discovery.monolith.service;

import java.time.LocalDateTime;
import java.util.List;

import it.discovery.monolith.domain.Notification;
import it.discovery.monolith.domain.Order;
import it.discovery.monolith.domain.OrderItem;
import it.discovery.monolith.repository.BookRepository;
import it.discovery.monolith.repository.CustomerRepository;
import it.discovery.monolith.repository.OrderRepository;

public class OrderService {

	private OrderRepository orderRepository;

	private BookRepository bookRepository;

	private CustomerRepository customerRepository;

	private NotificationService notificationService;

	private PaymentService paymentService;

	public void complete(int orderId) {
		orderRepository.findById(orderId).ifPresent(order -> {
			paymentService.pay(order);

			orderRepository.save(order);

			Notification notification = new Notification();
			notification.setEmail(order.getCustomer().getEmail());
			notification.setRecipient(order.getCustomer().getName());
			notification.setTitle("Order " + order.getId() + " is completed");
			notification.setText("Hi/n. Your order has been completed");

			notificationService.sendNotification(notification);
		});
	}

	public void cancel(int orderId) {
		orderRepository.findById(orderId).ifPresent(order -> {
			if (order != null) {
				order.setCancelled(true);
				orderRepository.save(order);

				Notification notification = new Notification();
				notification.setEmail(order.getCustomer().getEmail());
				notification.setRecipient(order.getCustomer().getName());
				notification.setTitle("Order " + order.getId() + " is canceled");
				notification.setText("Hi/n. Your order has been canceled");

				notificationService.sendNotification(notification);

			}
		});
	}

	public Order createOrder(int bookId, int number, int customerId) {
		Order order = new Order();
		order.addItem(new OrderItem(bookRepository.getOne(bookId), number));
		order.setOrderDate(LocalDateTime.now());
		order.setCustomer(customerRepository.getOne(customerId));

		return order;
	}

	public void addBook(int orderId, int bookId, int number) {
		orderRepository.findById(orderId).ifPresent(order -> {
			order.addItem(new OrderItem(bookRepository.getOne(bookId), number));
			orderRepository.save(order);
		});
	}

	public List<Order> findOrders() {
		return orderRepository.findAll();
	}

	public Order findOrderById(int orderId) {
		return orderRepository.getOne(orderId);
	}

}
