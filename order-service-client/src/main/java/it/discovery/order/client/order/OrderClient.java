package it.discovery.order.client.order;

import it.discovery.order.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Component
public class OrderClient implements OrderFacade {
    private final RestTemplate restTemplate;

    private final String baseUrl;

    public OrderClient() {
        this.restTemplate = new RestTemplate();
        baseUrl = "http://order:8080/orders";
    }

    @Override
    public List<OrderDTO> findAll() {
        return Arrays.asList(
                restTemplate.getForObject(baseUrl, OrderDTO[].class));
    }

    @Override
    public OrderDTO findById(int orderId) {
        return restTemplate.getForObject(baseUrl + "/" + orderId, OrderDTO.class);
    }
}
