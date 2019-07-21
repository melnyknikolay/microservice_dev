package it.discovery.order.controller;

import it.discovery.order.domain.Customer;
import it.discovery.order.repository.jpa.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customers")
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping
    public void saveCustomer(@RequestBody Customer customer) {
        customerRepository.save(customer);
    }

    @GetMapping
    public List<Customer> findCustomers() {
        return customerRepository.findAll();
    }
}
