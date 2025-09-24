package com.supply_chain_backend.inventory_service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final ProductRepository productRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC = "inventory-events";

    public Product addProduct(ProductDto dto){
        Product product = Product.builder()
                .sku(dto.getSku())
                .name(dto.getName())
                .description(dto.getDescription())
                .quantity(dto.getQuantity())
                .warehouseLocation(dto.getWarehouseLocation())
                .reorderLevel(dto.getReorderLevel())
                .build();
        return productRepository.save(product);
    }
    public Product updateStock(String sku, int quantityChange){
        Product product = productRepository.findBySku(sku)
                .orElseThrow(()-> new RuntimeException("Product not found"));
        product.setQuantity(product.getQuantity() + quantityChange);
        Product updated = productRepository.save(product);

        if (updated.getQuantity() <= updated.getReorderLevel()) {
            kafkaTemplate.send(TOPIC, "Low stock alert for SKU: " + updated.getSku());
        }
        return updated;

    }
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
    public Product getProduct(String sku){
        return productRepository.findBySku(sku)
                .orElseThrow(()-> new RuntimeException("Product not found!"));
    }
    public void removeProduct(String sku){
        Product product = getProduct(sku);
        productRepository.delete(product);
    }

}
