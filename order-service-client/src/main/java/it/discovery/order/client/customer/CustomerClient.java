package it.discovery.order.client.customer;

import it.discovery.order.dto.CustomerDTO;
import it.discovery.order.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Component
public class CustomerClient implements CustomerFacade {
    private final RestTemplate restTemplate;

    private final String baseUrl;

    public CustomerClient() {
        this.restTemplate = new RestTemplate();
        baseUrl = "http://order:8080/customers";
    }

    @Override
    public List<CustomerDTO> findAll() {
        return Arrays.asList(
                restTemplate.getForObject(baseUrl, CustomerDTO[].class));
    }

    @Override
    public String save(CustomerDTO customer) {
        return restTemplate.postForLocation(baseUrl, CustomerDTO.class).toString();
    }
}
