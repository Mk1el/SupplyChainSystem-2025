package com.supply_chain_backend.order_service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.supply_chain_backend.order_service.ProductResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
@FeignClient(name = "inventory-service", url = "http://localhost:8081") // change port
public interface InventoryClient {

    @GetMapping("/api/inventory/{sku}")
    ProductResponseDto getProductBySku(@PathVariable("sku") String sku);
}
