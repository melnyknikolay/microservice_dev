package it.discovery.payment.service;

import it.discovery.order.dto.OrderDTO;

public class PaymentProvider {

    public void charge(OrderDTO order) {
        if (order.getAmount() > order.getCustomer().getBalance()) {
            throw new RuntimeException("Not enough balance for order " + order.getId());
        }

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Charging credit card ... " + order.getCustomer().getCardNumber());
    }

}
