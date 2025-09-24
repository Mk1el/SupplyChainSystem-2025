package com.supply_chain_backend.order_service;
import com.supply_chain_backend.
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "order-events";

    public Order createOrder(OrderDto dto){
        ProductResponseDto product = inventoryClient.getProductBySku(dto.getProductSku());

    }
}
