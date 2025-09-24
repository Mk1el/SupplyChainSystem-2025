package com.supply_chain_backend.inventory_service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private String sku;
    private String name;
    private String description;
    private int quantity;
    private String warehouseLocation;
    private int reorderLevel;

}
