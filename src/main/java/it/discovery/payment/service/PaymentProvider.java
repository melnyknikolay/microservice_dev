package it.discovery.payment.service;

import it.discovery.monolith.domain.Order;

public class PaymentProvider {

	public void charge(Order order) {
		if (order.getAmount() > order.getCustomer().getBalance()) {
			throw new RuntimeException("Not enough balance for order " + order.getId());
		}

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Charging credit card ... " + order.getCustomer().getCardNumber());

		order.getCustomer().setBalance(order.getCustomer().getBalance() - order.getAmount());

	}

}
