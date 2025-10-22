package com.supply_chain_backend.order_service;

import lombok.Data;

@Data
public class ProductResponseDto {
    private String sku;
    private String name;
    private String description;
    private int quantity;
    private String warehouseLocation;
    private int reorderLevel;
}
