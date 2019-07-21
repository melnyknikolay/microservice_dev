package it.discovery.order.client.customer;

import it.discovery.order.dto.CustomerDTO;

import java.util.List;

public interface CustomerFacade {
    List<CustomerDTO> findAll();

    String save(CustomerDTO customer);
}
