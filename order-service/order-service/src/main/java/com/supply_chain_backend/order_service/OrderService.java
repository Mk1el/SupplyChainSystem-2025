package com.supply_chain_backend.order_service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final InventoryClient inventoryClient;

    private static final String TOPIC = "order-events";

    // Create Order
    public Order createOrder(OrderDto dto) {
        ProductResponseDto product = inventoryClient.getProductBySku(dto.getProductSku());

        // Validate stock
        if (product.getQuantity() < dto.getQuantity()) {
            throw new RuntimeException("Not enough stock in inventory!");
        }

        // Build and save order
        Order order = Order.builder()
                .orderNumber("ORD-" + System.currentTimeMillis())
                .customerName(dto.getCustomerName())
                .productSku(dto.getProductSku())
                .quantity(dto.getQuantity())
                .warehouseAssigned(product.getWarehouseLocation())
                .status("PENDING")
                .createdAt(LocalDateTime.now())
                .build();

        Order savedOrder = orderRepository.save(order);

        // Send Kafka event
        kafkaTemplate.send(TOPIC, "Order created: " + savedOrder.getId());
        return savedOrder;
    }

    // Read all
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Read one
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    // Update status
    public Order updateOrderStatus(Long id, String status) {
        Order order = getOrderById(id);
        order.setStatus(status);
        Order updatedOrder = orderRepository.save(order);

        kafkaTemplate.send(TOPIC, "Order " + id + " status updated to " + status);
        return updatedOrder;
    }

    // Delete order
    public void deleteOrder(Long id) {
        Order order = getOrderById(id);
        orderRepository.delete(order);

        kafkaTemplate.send(TOPIC, "Order deleted: " + id);
    }

    // Generate invoice
    public String generateInvoice(Long id) {
        Order order = getOrderById(id);
        return " Invoice for Order #" + order.getOrderNumber() +
                "\nCustomer: " + order.getCustomerName() +
                "\nProduct SKU: " + order.getProductSku() +
                "\nQuantity: " + order.getQuantity() +
                "\nWarehouse: " + order.getWarehouseAssigned() +
                "\nStatus: " + order.getStatus() +
                "\nDate: " + order.getCreatedAt();
    }
}
